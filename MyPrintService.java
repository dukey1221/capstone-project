/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lentrix.storemanager;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.event.PrintJobAdapter;
import javax.print.event.PrintJobEvent;

/**
 *
 * @author hawkm
 */
public class MyPrintService {
    public static void printString(String str) throws Exception {
        try {
            String defaultPrinter =
            PrintServiceLookup.lookupDefaultPrintService().getName();
            System.out.println("Default printer: " + defaultPrinter);
            PrintService service = PrintServiceLookup.lookupDefaultPrintService();

            StringBuffer data = new StringBuffer();

            char kick[] = {(char)27, (char)112, (char)48, (char)25,(char)250};
            String kickStr = String.valueOf(kick);

            data.append( kickStr );

            data.append("\n\n\n");
            data.append(str);
            data.append("\n\n\n");

            InputStream is = new ByteArrayInputStream(data.toString().getBytes("UTF8"));

            PrintRequestAttributeSet  pras = new HashPrintRequestAttributeSet();
            pras.add(new Copies(1));

            DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
            Doc doc = new SimpleDoc(is, flavor, null);
            DocPrintJob job = service.createPrintJob();

            PrintJobWatcher pjw = new PrintJobWatcher(job);
            job.print(doc, pras);
            pjw.waitForDone();
            is.close();
        }catch(UnsupportedEncodingException ex) {
            throw new Exception("Data encoding problem! Please contact developer.");
        }catch(PrintException ex) {
            throw new Exception("Printer problem! Please contact developer.");
        }catch(IOException ex) {
            throw new Exception("Unknown error! Please contact developer.");
        }
    }
}

class PrintJobWatcher {
  boolean done = false;

  PrintJobWatcher(DocPrintJob job) {
    job.addPrintJobListener(new PrintJobAdapter() {
      public void printJobCanceled(PrintJobEvent pje) {
        allDone();
      }
      public void printJobCompleted(PrintJobEvent pje) {
        allDone();
      }
      public void printJobFailed(PrintJobEvent pje) {
        allDone();
      }
      public void printJobNoMoreEvents(PrintJobEvent pje) {
        allDone();
      }
      void allDone() {
        synchronized (PrintJobWatcher.this) {
          done = true;
          System.out.println("Printing done ...");
          PrintJobWatcher.this.notify();
        }
      }
    });
  }
  public synchronized void waitForDone() {
    try {
      while (!done) {
        wait();
      }
    } catch (InterruptedException e) {
    }
  }
}