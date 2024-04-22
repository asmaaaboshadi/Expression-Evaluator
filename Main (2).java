import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

interface IExpressionEvaluator {
  

  
public String infixToPostfix(String expression);
  
  

public int evaluate(String expression);

}


public class Evaluator implements IExpressionEvaluator {
    public int a, b, c;
    private static class Node 
    {
        private Object element;
        private Node next; 
        public Node(Object E, Node N) 
        {
            element = E; 
            next = N;
        }
        public Node(Object E) 
        {
            element = E;
            next = null;
        }
        
        public Object getElement() {return element;}
        public void setNext(Node N) { next = N;} 
    }
    
    private Node top = null;
    private int size = 0; 
    public Object pop()
    {
        Object X;
        Node tmp = top.next;
        X = top.element; 
        top = tmp; 
        size--; 
        return X;
    }
    
    public Object peek()
    {
        if(!isEmpty())
        {
            return top.element;
        }
        else
        {
            return 0;
        }
    }
    public void push(Object element)
    {
        Node N = new Node(element, top);
        top = N; 
        size++; 
    }
    
    public boolean isEmpty()
    {
        return top == null;
    }
    public int degree(char c)
    {
        if(c == '+' || c == '-')
        {
            return 1;
        }
        else if(c == '*' || c == '/')
        {
            return 2;
        }
        else if(c == '^')
        {
            return 3;
        }
        return -1;
    }
    public String infixToPostfix(String expression)
    {
        String post = ""; 
        Evaluator stack = new Evaluator(); 
        for(int i = 0; i < expression.length(); i++)
        {
            char Y= expression.charAt(i); 
        
            if(Character.isLetterOrDigit(Y))
            {
                post += Y;
            }
            else if(Y == '(')
            {
                stack.push(Y);
            }
            else if(Y == ')')
            {
                  try{
                    while(!stack.isEmpty() && stack.peek()!= (Character)'(')
                    {
                        post+= stack.pop();
                    }
                    stack.pop();
                    }
                catch(Exception E)
                {
                    return "Error";  
                }
            }
            else
            {
                while(!stack.isEmpty() && degree(Y) <= degree((Character)stack.peek()))
                {
                    post+= stack.pop();
                }
                stack.push(Y);
            }
        }
        while(!stack.isEmpty())
        {
            if(stack.peek() == (Character)'(')
            {
                return "Error";
            }
            post += stack.pop(); 
        }
        return post;
    }
    public boolean checkErrors(String expression)
    {
        for(int i = 0; i < expression.length() - 1; i++)
        {
            char Y = expression.charAt(i);
            char Y1 = expression.charAt(0);
            char Y2 = expression.charAt(i+1);
            char Y3 = expression.charAt(expression.length() - 1);
            if((Y == '+' ||Y == '-' || Y == '*' || Y == '/' || Y == '^') && (Y2 == '+' ||Y2 == '-' ||                                    Y2 =='*' ||Y2 == '/' || Y2 == '^' || Y2 == ')'))
            {
                return true;
            }
            if((Y3 == '+' ||Y3 == '-' || Y3 == '*' || Y3 == '/' || Y3 == '^'))
            {
                return true;
            }
            if(Y == '(' && !Character.isLetter(Y2))
            {
                return true;
            }
         if((Y == 'a'||Y == 'b'|| Y == 'c'|| Y ==')' ) && (Y2 == 'a' || Y2 == 'b' || Y2 == 'c'||Y2 =='('))
            {
                return true;
            }
            if(Y1 =='+' || Y1 =='*' || Y1 =='/' || Y1 =='^')
            {
      
                return true;
            }
        }
        return false;
    }
    public int evaluate(String expression)
    {  
        int evaluation = 0; 
        double power = 0;
        Evaluator stack = new Evaluator();
        char l1 = '0', l2 = '0';
        try{
        for(int i = 0; i < expression.length(); i++)
        {
            char Y = expression.charAt(i);
            if(Character.isLetter(Y)) 
            {
                if(Y == 'a')
                {
                    stack.push(a);
                }
                else if(Y == 'b')
                {
                    stack.push(b);
                }
                else if(Y == 'c')
                {
                    stack.push(c);
                }
                else
                {
                    System.out.println("Error"); 
                    System.exit(0);
                }
            }
            else if(Character.isDigit(Y))
            {
                System.out.println("Error");
                System.exit(0);
            }
            else
            { 
                int N1 = 0, N2 = 0; 
                if(stack.isEmpty())
                {
                    System.out.println("Error");
                    System.exit(0);
                }
                if(!stack.isEmpty())
                {
                    N2 = (Integer)stack.pop();
                }
                if(!stack.isEmpty())
                {
                    N1 = (Integer)stack.pop();
                }
                if(Y == '+')
                {
                    evaluation = N1+N2;
                    stack.push(evaluation);
                } 
                else if(Y == '-')
                {
                    N2 *= -1;
                    evaluation = N1 + N2;
                    stack.push(evaluation);
                }
                else if(Y == '*') 
                {
                    evaluation = N1 * N2;
                    stack.push(evaluation);
                }
                else if(Y== '/') 
                {
                    evaluation = N1 / N2;
                    stack.push(evaluation);
                }
                else if(Y == '^')
                {
                    evaluation = (int)Math.pow(N1, N2);
                    stack.push(evaluation);
                }
            }
        }
        evaluation = (Integer)stack.pop();
        }catch(Exception E)
        {
            System.out.println("Error");
            System.exit(0);
        }
        return evaluation;
    }
    
    
    public static void main(String[] args) {
         Evaluator E = new Evaluator();
        try
        {
            Scanner scan = new Scanner(System.in);
            String[] temp = new String[2];
            String exp = scan.nextLine();
            String Eval1 = scan.nextLine();
            temp = Eval1.split("=");
            E.a = Integer.parseInt(temp[1]);
            String Eval2 = scan.nextLine();
            temp = Eval2.split("=");
            E.b = Integer.parseInt(temp[1]);
            String Eval3 = scan.nextLine();
            temp = Eval3.split("=");
            E.c = Integer.parseInt(temp[1]);
            exp = exp.replace("+--", "+").replace("--", "").replace("^--", "^").replace("/--", "/");
            exp = exp.replace("--+", "+").replace("--", "").replace("--^", "^").replace("--/", "/");
            if(exp.contains("--") && exp.charAt(0)== '-' || exp.charAt(0)== '1')
            {
                exp = exp.replaceFirst("--", ""); 
            }
            exp = exp.replace("--", "+");       
            String postfix = E.infixToPostfix(exp);
            if(!E.checkErrors(exp))
            {
                int evaluation = E.evaluate(postfix);
                System.out.println(postfix);
                System.out.println(evaluation);
            }
            else
            {
                System.out.println("Error");
                System.exit(0);
            }
        }catch(Exception ex)
        {
            System.out.println("Error");
            System.exit(0);
        }
        
            
    }
}