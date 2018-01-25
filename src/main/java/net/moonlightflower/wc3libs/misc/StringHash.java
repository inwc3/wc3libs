package net.moonlightflower.wc3libs.misc;

import java.io.UnsupportedEncodingException;

public class StringHash {

    private static void mix(int[] abc){
        int a=abc[0];
        int b=abc[1];
        int c=abc[2];

        a -= b; a -= c; a ^= (c >>> 13);
        b -= c; b -= a; b ^= (a <<   8);
        c -= a; c -= b; c ^= (b >>> 13);
        a -= b; a -= c; a ^= (c >>> 12);
        b -= c; b -= a; b ^= (a <<  16);
        c -= a; c -= b; c ^= (b >>>  5);
        a -= b; a -= c; a ^= (c >>>  3);
        b -= c; b -= a; b ^= (a <<  10);
        c -= a; c -= b; c ^= (b >>> 15);

        abc[0] = a;
        abc[1] = b;
        abc[2] = c;
    }

    private static int toi(byte b){
        int i = b;
        if(b < 0)
            i+=256;
        return i;
    }

    private static int hash(byte[] k, int length, int initval){
        int idx = 0;
        int len = length;
        int[] abc = {0x9e3779b9, 0x9e3779b9, initval};

        while(len >= 12){
            abc[0] = abc[0] + (toi(k[0 +idx]) + (toi(k[1 +idx]) << 8) + (toi(k[ 2 +idx]) << 16) + (toi(k[ 3 +idx]) << 24));
            abc[1] = abc[1] + (toi(k[4 +idx]) + (toi(k[5 +idx]) << 8) + (toi(k[ 6 +idx]) << 16) + (toi(k[ 7 +idx]) << 24));
            abc[2] = abc[2] + (toi(k[8 +idx]) + (toi(k[9 +idx]) << 8) + (toi(k[10 +idx]) << 16) + (toi(k[11 +idx]) << 24));
            mix(abc);
            idx += 12;
            len -= 12;
        }

        abc[2] += length;
        switch(len){
            case 11: abc[2] =  abc[2] + (toi(k[idx +10]) << 24);
            case 10: abc[2] =  abc[2] + (toi(k[idx + 9]) << 16);
            case 9:  abc[2] =  abc[2] + (toi(k[idx + 8]) <<  8);

            case 8:  abc[1] =  abc[1] + (toi(k[idx + 7]) << 24);
            case 7:  abc[1] =  abc[1] + (toi(k[idx + 6]) << 16);
            case 6:  abc[1] =  abc[1] + (toi(k[idx + 5]) <<  8);
            case 5:  abc[1] =  abc[1] + (toi(k[idx + 4])      );

            case 4:  abc[0] =  abc[0] + (toi(k[idx + 3]) << 24);
            case 3:  abc[0] =  abc[0] + (toi(k[idx + 2]) << 16);
            case 2:  abc[0] =  abc[0] + (toi(k[idx + 1]) <<  8);
            case 1:  abc[0] =  abc[0] +  toi(k[idx + 0]       );
        }
        mix(abc);
        return abc[2];
    }

    public static int hash(String s) throws UnsupportedEncodingException {
        if(s.isEmpty()){
            return 0;
        }
        byte[] k = s.getBytes("UTF-8");
        for(int i=0; i != k.length; i++){
            if(k[i] < 'a' || k[i] >'z'){
                if(k[i] == '/'){
                    k[i] = '\\';
                }
            }else{
                k[i] = (byte) (k[i] - 32);
            }
        }
        return hash(k, k.length, 0);
    }

}
