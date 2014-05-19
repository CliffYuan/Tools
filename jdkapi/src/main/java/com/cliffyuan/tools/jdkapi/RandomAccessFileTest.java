package com.cliffyuan.tools.jdkapi;

import java.io.RandomAccessFile;

/**
 * Created by yuanyuanming on 14-5-19.
 */
public class RandomAccessFileTest {
    public static void main(String[] args)throws Exception{
        RandomAccessFile randomAccessFile=new RandomAccessFile("ratest.dat","rw");
        for(int i=0;i<5;i++){
            randomAccessFile.writeDouble(i*1.44);
        }
        randomAccessFile.close();;

        randomAccessFile=new RandomAccessFile("ratest.dat","rw");
        randomAccessFile.seek(2*8);

        randomAccessFile.writeDouble(48.123);
        randomAccessFile.close();

        randomAccessFile=new RandomAccessFile("ratest.dat","rw");
        for(int i=0;i<5;i++){
            System.out.println(randomAccessFile.readDouble());
        }
        randomAccessFile.close();

    }
}
