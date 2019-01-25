package main.part3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Part 3 - class mixedCourseList
 *
 * part3 teki circular listin kullanıldıgı class. clas yapısı
 * part1 deki course clasına benzer.
 *
 * INPUT FILE:Courses(CSV)(Updated).csv
 * @author Burak Ozdemir 141044027
 */
public class mixedCourseList<E>{
    /**
     * Data Field
     */
    private  myCircularLinkedList<String> code=new  myCircularLinkedList<>();
    private  myCircularLinkedList<String> courseTitle=new  myCircularLinkedList<String>();
    private Integer size;

    /**
     * Constructor
     * @param courseFıleName
     * @throws IOException
     * @throws FileNotFoundException
     */
    public mixedCourseList(String courseFıleName)throws IOException,FileNotFoundException
    {
        this.size=0;
        String COMMA = ";";
        BufferedReader fileReader = null;
        try {
            String line = "";
            fileReader = new BufferedReader(new FileReader(courseFıleName));
            line = fileReader.readLine();

            if(line==null){throw new IOException();}
            String[] toks=line.split(COMMA);

            String[] temp=line.split(COMMA);
            String[] tok=line.split(COMMA);
            int i=0;
            try(BufferedReader br = new BufferedReader(new FileReader(courseFıleName))) {
                for(String line2; (line2 = br.readLine()) != null; ) {
                    if(i==0)
                        temp=line2.split(COMMA);
                    else
                    {
                        tok = line2.split(COMMA);
                        //semester.add(Integer.parseInt(tok[0]));
                        code.add(tok[1],tok[0]);
                        courseTitle.add(tok[2],tok[0]);
                        this.size++;
                    }
                    i++;
                }
                // line is not visible here.
            }
            /*
            while ((line = fileReader.readLine()) != null) {
                String[] tok = line.split(COMMA);
                //semester.add(Integer.parseInt(tok[0]));
                code.add(tok[0],tok[1]);
                courseTitle.add(tok[0],tok[2]);
                this.size++;
            }*/
        }
        catch(IOException | NumberFormatException q){
            System.err.println(q.toString());
        }
    }

    /**
     * Main method
     * @param args
     */
    public static void main(String[] args){
        try{
            mixedCourseList test=new mixedCourseList("Courses(CSV)(Updated).csv");
            System.out.println("::::::::getByCode:::::::::");
            System.out.println("CSE 101: "+test.getByCode("CSE 101"));
            System.out.println("CSE 107: "+test.getByCode("CSE 107"));
            System.out.println("MATH 101: "+test.getByCode("MATH 101"));

            myCircularLinkedList<String> inSemestercourses=test.listSemesterCourses(1);
            myCircularLinkedList<String> inrangeCourse=test.getByRange(0,20);

            System.out.println("::::::::listSemesterCourses(1)::::::::::");
            ListIterator<String> iter=inSemestercourses.listIterator(0);
            while (iter.hasNext()){
                System.out.println(iter.next());
            }
            System.out.println(iter.next());
            System.out.println("-----------------------");
            System.out.println(":::::::::::getByRange(0,20)::::::::");
            iter=inrangeCourse.listIterator(0);
            while(iter.hasNext()){
                System.out.println(iter.next());
            }
            System.out.println("---------------");
            System.out.println("::::::::::listIterator InSemester(1)");
            iter=inrangeCourse.listIteratorInSemester(0);
            int count=0;
            while (count<20){
                System.out.println(iter.next());
                count++;
            }
        }catch (Exception a){

            System.out.println(a.getMessage());
        }
    }

    /**
     * parametre koduna göre coursun titleini return eder
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
     * girilen semester degerindeki tüm courseları circularliste atar ve return eder.
     * @param semester
     * @return myCircularLinkledList<E>
     * @throws Exception
     */
    public myCircularLinkedList<String> listSemesterCourses(int semester) throws Exception{
        myCircularLinkedList<String> result=new myCircularLinkedList<String>();
        for (int i=0;i<this.size;i++){
            //System.out.println();
            if(Integer.parseInt(this.code.getKey(i))==semester){
                result.add(this.code.get(i),this.code.getKey(i));
            }
        }
        if(result.size()==0)
            throw new Exception("There is no course");
        else
            return result;
    }

    /**
     * girilen index aralıklarndaki tüm dersleri circularlinkedlist e atar ve return eder.
     * @param start_index
     * @param last_index
     * @return myCircularLinkedList<E></>
     * @throws Exception
     */
    public myCircularLinkedList<String> getByRange(int start_index,int last_index) throws Exception{
        myCircularLinkedList<String> result=new myCircularLinkedList<String>();
        if(start_index>=0 && start_index<=last_index && last_index<=this.size && last_index>=start_index){
            for (int i=start_index;i<=last_index;i++){
                result.add(this.code.get(i),this.code.getKey(i));
            }
            return result;
        }
        else
            throw new Exception("wrong range value");

    }

    /**
     * Codu verilen courseın indisini return eder.
     * @param code
     * @return int
     */
    public int getIndexOfCode(String code){
        for (int i = 0; i <size ; i++) {
            //System.out.println(code+"---"+this.code.get(i));
            if(this.code.get(i).equals(code))
                return i;
        }
        return -1;
    }


}
