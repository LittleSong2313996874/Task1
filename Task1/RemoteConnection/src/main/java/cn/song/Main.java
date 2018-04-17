package main.java.cn.song;

import main.java.cn.song.connectWithPureJDBC.remoteCon2Test;


public class Main {


    //static Logger logger = Logger.getLogger(main.java.cn.song.Main.class);
    public static void main(String[] args) throws Exception{
        remoteCon2Test remote= new remoteCon2Test();
        remote.testForRemote(1000000);
        remote.getPersonRow(14646500);
    }
}
