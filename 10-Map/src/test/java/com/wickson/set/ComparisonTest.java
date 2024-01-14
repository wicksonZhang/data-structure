package com.wickson.set;

import com.wickson.file.FileInfo;
import com.wickson.file.Files;
import com.wickson.utils.Times;
import org.junit.jupiter.api.Test;

/**
 * 性能对比：红黑树和链表
 */
public class ComparisonTest {


    @Test
    public void ListSetTest() {
        Times.test("ListSet", () -> common(new ListSet<>(), file()));
    }

    @Test
    public void treeSetTest() {
        Times.test("TreeSet", () -> common(new TreeSet<>(), file()));
    }

    public String[] file() {
        FileInfo fileInfo = Files.read("C:\\Users\\wicks\\Desktop\\java-source\\java\\util",
                new String[]{"java"});

        System.out.println("文件数量：" + fileInfo.getFiles());
        System.out.println("代码行数：" + fileInfo.getLines());
        String[] words = fileInfo.words();
        System.out.println("单词数量：" + words.length);
        return words;
    }

    public void common(Set<String> set, String[] words) {
        // 添加
        for (String word : words) {
            set.add(word);
        }
        // 查询
        for (String word : words) {
            set.contains(word);
        }
        // 删除
        for (String word : words) {
            set.remove(word);
        }
    }

}
