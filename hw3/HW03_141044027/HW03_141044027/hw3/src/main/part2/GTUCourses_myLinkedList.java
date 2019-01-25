package main.part2;

import main.part1.GTUCECourses;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Part 2 - class GTUCECourses_myLinkedList
 *
 * part1 deki Course yapısını myLinkedList clası ile test edilmiş hali.
 * enable , disable ve showDisable methodları eklenmiştir.
 * disable özelliği boolean bir değişken ile kontrol ediliyor.
 *
 * INPUT FILE:Courses(CSV)(Updated).csv
 * @author Burak Ozdemir 141044027
 */
public class GTUCourses_myLinkedList {
    /**
     * Data Fields
     */
    private myLinkedList<Integer> semester=new myLinkedList<Integer>();
    private myLinkedList<String> code=new myLinkedList<String>();
    private myLinkedList<String> courseTitle=new myLinkedList<String>();
    private Integer size;

    /**
     * Main method
     * @param args
     */
    public static void main(String[] args) {
        try{

            GTUCourses_myLinkedList test=new GTUCourses_myLinkedList("Courses(CSV)(Updated).csv");
            System.out.println("::::::::::::getbyCode() ::::::::::");
            System.out.println("CSE 101: "+test.getByCode("CSE 101"));
            System.out.println("CSE 107: "+test.getByCode("CSE 107"));
            System.out.println("MATH 101: "+test.getByCode("MATH 101"));
            System.out.println("::::::::::::Semester_2 ::::::::::");
            myLinkedList<String> inSemestercourses=test.listSemesterCourses(2);
            ListIterator<String> iter=inSemestercourses.listIterator(0);
            while (iter.hasNext()){
                System.out.println(iter.next());
            }
            System.out.println("-------------------");
            System.out.println(":::::::::::Courses from 0 to 15::::::::::");
            myLinkedList<String> rangeCourses=test.getByRange(0,15);
            ListIterator<String> iter2=rangeCourses.listIterator(0);
            while (iter2.hasNext()){
                System.out.println(iter2.next());
            }
            test.size.toString();

        }catch (Exception a){
            System.out.println(a.getMessage());
        }
    }

    /**
     * Constructor
     * @param courseFıleName
     */
    public GTUCourses_myLinkedList(String courseFıleName){
        this.size=0;
        String COMMA = ";";
        BufferedReader fileReader = null;
        try {
            String line = "";
            fileReader = new BufferedReader(new FileReader(courseFıleName));
            line = fileReader.readLine();

            if(line==null){throw new IOException();}
            String[] toks=line.split(COMMA);

            while ((line = fileReader.readLine()) != null) {
                String[] tok = line.split(COMMA);
                semester.add(Integer.parseInt(tok[0]));
                code.add(tok[1]);
                courseTitle.add(tok[2]);
                this.size++;
            }
        }
        catch(IOException | NumberFormatException q){
            System.err.println(q.toString());
        }
    }

    /**
     * Code parametresi alıp indexini return eder
     * @param code
     * @return int
     */
    public int getIndexOfCode(String code){
        for (int i = 0; i <size ; i++) {
            if(this.code.get(i).equals(code)) return i;
        }
        return -1;
    }

    /**
     *Dersinc kodunu parametre olarak alir ve icerigini return eder.
     * @param code
     * @return String
     * @throws Exception
     */
    public String getByCode(String code) throws Exception{
        int flag=getIndexOfCode(code);
        if(flag!=-1){
            return this.courseTitle.get(flag);
        }
        else
            throw new Exception("There is no course");
    }

    /**
     * Semester parametresiyle ayni semesterdaki tum dersleri LinkedList olarak return eder.
     * @param semester
     * @return myLinkedList<E>
     * @throws Exception
     */
    public myLinkedList<String> listSemesterCourses(int semester) throws Exception{
        myLinkedList<String> result=new myLinkedList<String>();
        for (int i=0;i<this.size;i++){
            if(this.semester.get(i).equals(semester)){
                result.add(this.code.get(i));
            }
        }
        if(result.size()==0)
            throw new Exception("There is no course");
        else
            return result;
    }

    /**
     * start ve last indexleri arasindaki tüm dersleri LinkedList olarak return eder.
     * @param start_index
     * @param last_index
     * @return myLinkedList<E>
     * @throws Exception
     */
    public myLinkedList<String> getByRange(int start_index,int last_index) throws Exception{
        myLinkedList<String> result=new myLinkedList<String>();
        if(start_index>=0 && start_index<=last_index && last_index<=this.size && last_index>=start_index){
            for (int i=start_index;i<=last_index;i++){
                result.add(this.code.get(i));
            }
            return result;
        }
        else
            throw new Exception("wrong range value");

    }

}
