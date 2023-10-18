package com.zhirong.liaohui.util;

public class Md5Util {

    public static String decrypt(String ssoToken)
    {
        try
        {
            String name = new String();
            java.util.StringTokenizer st=new java.util.StringTokenizer(ssoToken,"%");
            while (st.hasMoreElements()) {
                int asc = Integer.parseInt((String)st.nextElement()) - 27;
                name = name + (char)asc;
            }

            return name;
        }catch(Exception e)
        {
            e.printStackTrace() ;
            return null;
        }
    }

    public static String encrypt(String ssoToken)
    {
        try
        {
            byte[] _ssoToken = ssoToken.getBytes("ISO-8859-1");
            String name = new String();
// char[] _ssoToken = ssoToken.toCharArray();
            for (int i = 0; i < _ssoToken.length; i++) {
                int asc = _ssoToken[i];
                _ssoToken[i] = (byte) (asc + 27);
                name = name + (asc + 27) + "%";
            }
            return name;
        }catch(Exception e)
        {
            e.printStackTrace() ;
            return null;
        }
    }

}
