package com.wickson.map;

import com.wickson.file.FileInfo;
import com.wickson.file.Files;
import com.wickson.utils.Times;
import org.junit.jupiter.api.Test;

/**
 * 性能对比：红黑树和链表
 */
public class ComparisonTest {

    @Test
    public void treeMapTest() {
        Times.test("treeMapTest", () -> common(new TreeMap<>(), file()));
    }

    public String[] file() {
        FileInfo fileInfo = Files.read("D:\\java\\jdk-8\\jdk1.8.0_211\\src\\java\\util",
                new String[]{"java"});

        System.out.println("文件数量：" + fileInfo.getFiles());
        System.out.println("代码行数：" + fileInfo.getLines());
        String[] words = fileInfo.words();
        System.out.println("单词数量：" + words.length);
        return words;
    }

    public void common(TreeMap<String, Integer> treeMap, String[] words) {
        // 添加
        for (String word : words) {
            Integer count = treeMap.get(word);
            Integer totalCount = count == null ? 1 : count + 1;
            treeMap.put(word, totalCount);
        }
        // 创建 Visitor 接口的实现类，用于测试遍历
        treeMap.traversal(new Map.Visitor<String, Integer>() {
            @Override
            public boolean visit(String key, Integer value) {
                System.out.println("key = " + key + ", " + "value = " + value);
                return false;
            }
        });
        // 查询
        for (String word : words) {
            treeMap.containKey(word);
        }
        // 删除
        for (String word : words) {
            treeMap.remove(word);
        }
    }

}
