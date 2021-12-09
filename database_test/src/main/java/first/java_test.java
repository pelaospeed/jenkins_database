package first;

import org.json.JSONObject;

public class java_test {
    public  static void main (String args[]){
        System.out.printf("Hola Mundo2 \n");
        int i = 1;
        int j = 1;
        String a = "Hola";
        if (i == j){
            System.out.println("Equals");
        }else{
            System.out.println("Not equals");
        }
        if (a.equals(j)){
            System.out.println("Equals");
        }else{
            System.out.println("Not equals");
        }
/*        if (!(a == j)){
            System.out.println("Equals");
        }else{
            System.out.println("Not equals");
        }*/

        String hola = "{Hola mundo: en JSON}";
        JSONObject jsonObj2 = new JSONObject(hola);
        System.out.println(jsonObj2.toString());

    }
}
