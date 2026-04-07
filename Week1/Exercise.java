// package Week1;
abstract class  Shape{
    abstract double area();
    abstract double perimeter();
   
}
 class Circle extends Shape{
    
    double radius;
    @Override
    public double area(){
        return 3.14*radius*radius;
    }
    public double perimeter(){
        return 2*3.14*radius;
    }
 }

 class Triangle extends Shape{
   
    int b;int h;
    int l;
     @Override
    public double area(){
           return 0.5*b*h;
    }
      public double perimeter(){
        return l+b+h;
    }
 }
 class Rectangle extends Shape{
    int b;
    int l;
    @Override
    public double area(){
           return l*b;
    }
      public double perimeter(){
        return 2*(l+b);
    }
 }
public class Exercise{
    public static void main(String[] args) {
         Triangle obj = new Triangle();
         obj.l=5;
         obj.b=6;
         obj.h=4;
         double Areaoftriangle = obj.area();
         double perimeterofTriangle= obj.perimeter();
         System.out.println("Area of triangle : "+Areaoftriangle+",Perimeter of triangle :"+perimeterofTriangle);
          Circle obd = new Circle();
          obd.radius = 5;
          double AreaofCircle = obd.area();
          double Cicumferrence = obd.perimeter();
          System.out.println("Area of Circle : "+AreaofCircle +",Cicumferrence of Circle : "+Cicumferrence);
          Rectangle obs = new Rectangle();
          obs.l=6;
          obs.b=8;
          double Arearect = obs.area();
          double perimt = obs.perimeter();
          System.out.println("Area of Rectangle : "+Arearect+",perimeter of Rectangle : "+perimt);

    }

    
}
