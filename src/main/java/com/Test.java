package com;
import jdk.nashorn.internal.runtime.ScriptEnvironment;

import javax.script.ScriptEngineManager;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.Month;
/**
 * Created by hanzhiqiang@lenztechretail.com
 * on 2019/6/17.
 */
public class Test {
    public static void main(String args[]){
        Test java8tester = new Test();
        java8tester.testLocalDateTime();

        System.out.println(LocalDate.now());
    }
    public void testLocalDateTime(){

        // ��ȡ��ǰ������ʱ��
        LocalDateTime currentTime = LocalDateTime.now();
        System.out.println("��ǰʱ��: " + currentTime);

        LocalDate date1 = currentTime.toLocalDate();
        System.out.println("date1: " + date1);

        Month month = currentTime.getMonth();
        int day = currentTime.getDayOfMonth();
        int seconds = currentTime.getSecond();

        System.out.println("��: " + month +", ��: " + day +", ��: " + seconds);

        LocalDateTime date2 = currentTime.withDayOfMonth(10).withYear(2012);
        System.out.println("date2: " + date2);

        // 12 december 2014
        LocalDate date3 = LocalDate.of(2014, Month.DECEMBER, 12);
        System.out.println("date3: " + date3);

        // 22 Сʱ 15 ����
        LocalTime date4 = LocalTime.of(22, 15);
        System.out.println("date4: " + date4);

        // �����ַ���
        LocalTime date5 = LocalTime.parse("20:15:30");
        System.out.println("date5: " + date5);
    }
}
