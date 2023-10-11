package tools;


import java.util.ArrayList;

public class ProcessTools {

    //loginProcessMessage:将学生登录的账号密码转换成发送的String
    //转换后的字符串为: userName|userNumber
    public static String loginProcessMessage(String userName,String userNumber) {

        StringBuffer tempLoginMessage = new StringBuffer("");
        tempLoginMessage.append(userName);
        tempLoginMessage.append('-');
        tempLoginMessage.append(userNumber);

        String loginMessage = tempLoginMessage.toString();

        return loginMessage;
    }

    /**
     * 判断某个字符串是否是一个 IP 地址
     *
     * @param str 字符串
     */
    public static boolean isIPAddress(String str) {
        // 如果长度不符合条件 返回false
        /*if(str.length()<7 || str.length() >15) return false;
        String[] arr = str.split("\\.");
        //如果拆分结果不是4个字串 返回false
        if( arr.length != 4 )    return false;
        for(int i = 0 ; i <4 ; i++ ){
            for(int j = 0; j<arr[i].length();j++){
                char temp = arr[i].charAt(j);
                //如果某个字符不是数字就返回false
                if(!( temp>'0' && temp< '9' ) ) return false;
            }
        }
        for(int i = 0 ; i<4;i++){
            int temp = Integer.parseInt( arr[i] );
            //如果某个数字不是0到255之间的数 就返回false
            if( temp<0 || temp >255)    return false;
        }*/
        return true;
    }
    public static int sameNumberCount(ArrayList<Integer> a, ArrayList<Integer> b) {
        int answer = 0;
        for(int i = 0; i <= a.size() ;i ++ ) {
            for(int j = 0;j <= b.size() ;j ++ ) {
                if(a.get(i).equals(b.get(j))) {
                    answer ++ ;
                    continue;
                }
            }
        }
        return answer;
    }
    public boolean judge(String message) {
        String userName = "";
        String userNumber = "";
        int point = -1;
        for(int i = 0;i < message.length(); i ++ ) {
            if(message.charAt(i) == '|') {
                point = i;
            }
        }
        //userName = message.subSequence(0, point);
        //userNumber = message.subSequence(point, message.length() - 1);
        return true;
    }
    public static String separateString(String string, int separateFlag) {
        StringBuffer temp = new StringBuffer(string);
        int count = 0;
        for(int i = separateFlag; i < temp.length();i += separateFlag) {
            temp.insert(separateFlag,"\n");
        }
        String answerString = temp.toString();
        return answerString;
    }
}
