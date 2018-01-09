package com.breadcrumbs.breadcrumbs.node.service;

import org.rosuda.JRI.REXP;
import org.rosuda.JRI.Rengine;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.REngineException;
import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;

public class RServer {

    private RConnection c = null;
    static String code=null;
    
    public RServer(String codes) throws RserveException {
        code=codes;   
    	c = new RConnection();
    }
    public String getResult() throws RserveException, REXPMismatchException {
           org.rosuda.REngine.REXP x = c.eval(code);
           return x.asString();
    }
    
//    private void getDoubles() throws RserveException, REXPMismatchException {
//           org.rosuda.REngine.REXP x = c.eval("rnorm(100)");
//           double[] d = x.asDoubles();
//           for (int i = 0; i < d.length; i++) {
//                   System.out.println(d[i]);
//           }
//    }

//    private void getDataFrame1() throws REngineException, REXPMismatchException {
//           double[] dataX = { 10, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
//           double[] dataY = { 10, 11, 12, 13, 14, 15, 16, 17, 18, 19 };
//
//           c.assign("x", dataX);
//           c.assign("y", dataY);
//
//           org.rosuda.REngine.RList l = c.eval("lowess(x,y)").asList();
//           double[] lx = l.at("x").asDoubles();
//           String[] ly = l.at("y").asStrings();
//
//           for (int i = 0; i < lx.length; i++) {
//                   System.out.println(lx[i]);
//           }
//           System.out.println("==============================");
//           for (int i = 0; i < ly.length; i++) {
//                   System.out.println(ly[i]);
//           }      
//    }

//    private void getDataFrame2() throws RserveException,REXPMismatchException {      
//           org.rosuda.REngine.RList l = c.eval("{d=data.frame(\"TestData\",c(11:20));lapply(d,as.character)}").asList();
//           int cols = l.size();
//           int rows = l.at(0).length();
//           String[][] s = new String[cols][];
//
//           for (int i = 0; i < cols; i++) {
//                   s[i] = l.at(i).asStrings();
//           }
//           for (int i = 0; i < cols; i++) {
//                   for (int j = 0; j < rows; j++) {
//                          System.out.println(s[i][j]);
//                   }
//           }
//    }      

//    public static void main(String[] args) throws REXPMismatchException,
//    	REngineException {
//           RServer RServe = new RServer();
//           System.out.println("------------버젼 가져오기--------------");
//           RServe.getRVersion();
//           System.out.println("------------더블 데이터 가져오기-------------");
//           RServe.getDoubles();          
//           System.out.println("------------데이터 주입 연산후 가져오기------");
//           RServe.getResult();
           
           
           
//           System.out.println("------------데이터 생성 연산후 가져오기------");
//           RServe.getDataFrame2();
//    }
}