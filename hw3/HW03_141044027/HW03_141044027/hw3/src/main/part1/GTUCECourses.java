package main.part1;

import com.sun.net.ssl.SSLPermission;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Part 1 - class GTUCECourses
 * INPUT FILE:Courses(CSV)(Updated).csv
 * @author Burak Ozdemir 141044027
 */
public class GTUCECourses {
    /**
     * Data Fields
     */
    private LinkedList<Integer> semester=new LinkedList<Integer>();
    private LinkedList<String> code=new LinkedList<String>();
    private LinkedList<String> courseTitle=new LinkedList<String>();
    private Integer size;

    /**
     * Main method
     * @param args
     */
    public static void main(String[] args) {
        try{
            GTUCECourses test=new GTUCECourses("Courses(CSV)(Updated).csv");
            System.out.println("test.getByCode:::::::");
            System.out.println("CSE 101:"+test.getByCode("CSE 101"));
            System.out.println("CSE 107:"+test.getByCode("CSE 107"));
            System.out.println("MATH 101:"+test.getByCode("MATH 101"));
            System.out.println("test.listSemester(3):::::::");
            LinkedList<String> listSemester=test.listSemesterCourses(3);
            ListIterator<String> iter=listSemester.listIterator(0);
            while(iter.hasNext()){
                System.out.println(iter.next());
            }
            System.out.println("test.getByRange(0,10)::::");
            LinkedList<String> byrange=test.getByRange(0,10);
            iter=byrange.listIterator(0);
            while(iter.hasNext()){
                System.out.println(iter.next());
            }

        }catch (Exception a){
            System.out.println(a.getMessage());
        }
    }

    /**
     * Constructor
     * @param courseFıleName
     * @throws IOException
     * @throws FileNotFoundException
     */
    public GTUCECourses(String courseFıleName) throws IOException,FileNotFoundException
    {
        this.size=0;
        String COMMA = ";";
        BufferedReader fileReader = null;
        try {
            String line = "";
            fileReader = new BufferedReader(new FileReader(courseFıleName));
            line = fileReader.readLine();

            if(line==null){throw new IOException();}

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
                        semester.add(Integer.parseInt(tok[0]));
                        code.add(tok[1]);
                        courseTitle.add(tok[2]);
                        this.size++;
                    }
                    i++;
                }
                // line is not visible here.
            }
        }
        catch(IOException | NumberFormatException q){
            System.err.println(q.toString());
        }
    }

    /**
     * Dersinc kodunu parametre olarak alir ve icerigini return eder.
     * @param code
     * @return String
     * @throws Exception
     */
    public String getByCode(String code) throws Exception{
        int flag=this.code.indexOf(code);
        //System.out.println(flag);
        if(flag!=-1){
            return this.courseTitle.get(flag);
        }
        else
            throw new Exception("There is no course");
    }

    /**
     * Semester parametresiyle ayni semesterdaki tum dersleri LinkedList olarak return eder.
     * @param semester
     * @return LinkedList
     * @throws Exception
     */
    public LinkedList<String> listSemesterCourses(int semester) throws Exception{
        LinkedList<String> result=new LinkedList<String>();
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
     * @return LinkedList
     * @throws Exception
     */
    public LinkedList<String> getByRange(int start_index,int last_index) throws Exception{
        LinkedList<String> result=new LinkedList<String>();
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
