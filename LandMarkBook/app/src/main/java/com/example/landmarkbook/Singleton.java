package com.example.landmarkbook;

public class Singleton {
    private landMark sentLendMark;
    private static Singleton singleton;

    private Singleton(){

    }
    public landMark getSentLendMark(){
        return sentLendMark;
    }

    public void setSentLendMark(landMark sentLendMark) {
        this.sentLendMark = sentLendMark;
    }

    public static Singleton getSingleton() {
        if (singleton == null){
            singleton = new Singleton();
        }
        return singleton;
    }
}
