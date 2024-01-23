package ru.darek;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.Duration;

public class MyArrLibTest {
    private static int[] arr;
    private static int[] arrThrow;
    private static int[] standart;
    @BeforeAll
    public static void initAll(){
        arr = new int[]{2, 3, 5, 1, 6, 9, 90};
        standart = new int[]{6, 9, 90};
        arrThrow = new int[]{ 90,3,67,3456,43,0};
    }
    @BeforeEach
    public void initEach(){
        /* Инициализация перед каждым тестом */
    }
    @Test
    public void GetArrAfterOneCorrect(){
        Assertions.assertAll(
                () -> Assertions.assertArrayEquals(standart,MyArrLib.GetArrAfterOne(arr)),
                () -> Assertions.assertArrayEquals(new int[]{7},MyArrLib.GetArrAfterOne(new int[]{2,3,4,6,1,7})),
                () -> Assertions.assertArrayEquals(new int[]{2,3,4,6,10,7},MyArrLib.GetArrAfterOne(new int[]{1,2,3,4,6,10,7})),
                () -> Assertions.assertArrayEquals(new int[]{},MyArrLib.GetArrAfterOne(new int[]{12,33,544,600,1,1}))
        );
    }
    @CsvSource({
    "6,1,7,7",
    "2,1,4,4",
    "0,1,2,2"
    })
    @ParameterizedTest
    public void GetArrAfterOneCorrectParam(int a,int b,int c,int result){
               Assertions.assertArrayEquals(new int[]{result},MyArrLib.GetArrAfterOne(new int[]{a,b,c}));
    }
    @Test
    public void GetArrAfterOneTimeout(){
        Assertions.assertTimeout(Duration.ofNanos (100), () -> {MyArrLib.GetArrAfterOne(arr);},"Превышен таймаут в 100нс");
    }
    @Test
    public void GetArrAfterOneNotSame(){
        Assertions.assertNotSame(arr,MyArrLib.GetArrAfterOne(arr),"Метод вернул ту же ссылку!");
    }
    @Test
    public void GetArrAfterOneThrow(){
        Assertions.assertThrowsExactly(RuntimeException.class,() -> {MyArrLib.GetArrAfterOne(arrThrow);},"Не получили ожидаемого RuntimeException!");
    }
}
