import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class GrammarExercise {
    public static void main(String[] args) {
        //需要从命令行读入
        String firstWordList = "apple,juice,mother,people,beautiful,apple,dog";
        String secondWordList = "cat,baby,smile,good,apple,beautiful,Dog,nice";
        //读入检测串
        /*Scanner sc =new Scanner(System.in);
        if(!sc.hasNext()){
            sc.close();
            return;
        }*/
        //firstWordList = sc.nextLine();
        //secondWordList = sc.nextLine();
        //sc.close();
        List<String> result = findCommonWordsWithSpace(firstWordList,secondWordList);

        //按要求输出到命令行
        result.stream().forEach(a->System.out.println(a));
    }

    public static List<String> findCommonWordsWithSpace(String firstWordList, String secondWordList) {
        //获取单词数组并校验
        List<List<String>> inputList= Arrays.asList(firstWordList, secondWordList).stream()
                                                                                  .map(wordList->{String upperList = wordList.toUpperCase(); return splitAndCheck(upperList);})
                                                                                  .collect(Collectors.toList());
        List<String> result = inputList.get(0).stream()
                                              .distinct()
                                              .filter(item -> inputList.get(1).contains(item))
                                              .collect(Collectors.toList());
        String regex = "(.{1})";
        return result.stream()
                     .sorted()
                     .map(a->{String p = a.replaceAll(regex,"$1 "); return p.substring(0,p.length()-1);})
                     .collect(Collectors.toList());
    }

    /**
     * 字符串分割与输入校验
     * @param wordList
     * @return
     */
    private static List<String> splitAndCheck(String wordList) {
        List<String> listword = Arrays.asList(wordList.split(","));
        listword.stream().forEach(a->{if(a == null || a.length()==0 || a.length()!= countUpperCase(a))
                                throw new RuntimeException("input not valid");});
        return listword;
    }

    /**
     * 计算输入字符串的大写字母个数
     * @param str
     * @return
     */
    private static int countUpperCase(String str) {
        int uppercase = 0;
        for(int i = 0;i < str.length();i++){//循环遍历该字符串中的每个字符，下标元素从0开始到str.length()-1结束。
            char ch = str.charAt(i);//因为以下方法接受的参数是一个字符变量，所有要用 字符串变量.charAt(位置，也就是下标指到的地方)中的字符提取出来给它单独的放入字符变量ch中;
            if(Character.isLetter(ch)){//如果是字母，然后在进行大小写判断
                if(Character.isUpperCase(ch)){//如果是大写字母
                    uppercase++;
                }
            }
        }
        return uppercase;
    }
}
