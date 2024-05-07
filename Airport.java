import java.util.*;



public class Airport {
    Scanner scanner = new Scanner(System.in);
    private final Plane[] bounds;
    private final ArrayList<Integer> emptyBounds;
    private final Map<String,Plane> planes;

    public Airport(){
        int planesNumber = scanner.nextInt();
        int boundsNumber = scanner.nextInt();


        bounds = new Plane[boundsNumber];
        planes = new HashMap<>();
        emptyBounds = new ArrayList<>(boundsNumber);

        for (int i = 0; i < boundsNumber; i++)
            emptyBounds.add(i,i+1);


        for (int i = 0; i < planesNumber; i++){
            Plane plane = new Plane(scanner.next());
            planes.put(plane.getId(),plane);
        }
        int actionsCounter = scanner.nextInt();
        for (int i = 0; i < actionsCounter ; i++) {
            String action= scanner.next();
            String ID= scanner.next();
            action(action,ID);
        }
    }

    public static void main(String[] args) {
        new Airport();
    }

    private void takeOff(Plane plane) {
        switch (plane.getState()) {
            case 1 -> {
                if (!emptyBounds.isEmpty()) {
                    plane.setState(2);
                    bounds[emptyBounds.getFirst()-1]= plane;
                    emptyBounds.removeFirst();
                } else {
                    System.out.println("NO FREE BOUND");
                }
            }
            case 2 -> System.out.println("YOU ARE TAKING OFF");
            case 3 -> System.out.println("YOU ARE LANDING NOW");
            case 4 -> System.out.println("YOU ARE NOT HERE");
        }
    }
    private void Land(Plane plane){
        switch (plane.getState()) {
            case 1 -> System.out.println("YOU ARE HERE");
            case 2 -> System.out.println("YOU ARE TAKING OFF");
            case 3 -> System.out.println("YOU ARE LANDING NOW");
            case 4 -> {
                if (!emptyBounds.isEmpty()) {
                    plane.setState(3);
                    planes.put(plane.getId(),plane);
                    bounds[emptyBounds.getLast()-1]= plane;
                    emptyBounds.removeLast();
                } else {
                    System.out.println("NO FREE BOUND");
                }
            }
        }
    }
    private void planeStatus(Plane plane){
        System.out.println(plane.getState());
    }
    private void bandStatus(int ID){
        if(bounds[ID-1]== null)
            System.out.println("FREE");
        else
            System.out.println(bounds[ID-1]);
    }

    public void action(String action, String ID){
        Plane plane;
        if (planes.containsKey(ID)){
            plane = planes.get(ID);
        }
        else{
            plane = new Plane(ID,4);
        }
        switch (action) {
            case "TAKE-OFF" -> takeOff(plane);
            case "LANDING" -> Land(plane);
            case "PLANE-STATUS" -> planeStatus(plane);
            case "BAND-STATUS" -> bandStatus(Integer.parseInt(ID));
        }
    }
}


class Plane implements Comparable<Plane>{
    private int state;
    private final String id;

    public Plane(String ID , int state){
        this.id = ID;
        setState(4);
    }
    public Plane(String ID){
        this.id= ID;
        setState(1);
    }

    public String getId() {
        return id;
    }

    public void setState(int state) {
        if (state>0 && state <5)
            this.state = state;
        else System.out.println("wrong state");
    }

    public int getState() {
        return state;
    }



    @Override
    public boolean equals (Object obj) {
        if (obj instanceof Plane) {
            return this.id.equals(((Plane) obj).id);
        }else if (obj instanceof String){
            return this.id.equals(obj);
        }else {
            return super.equals(obj);
        }
    }

    @Override
    public int compareTo(Plane o) {
        return this.id.compareTo(o.id);
    }


    @Override
    public String toString() {
        return this.id;
    }
}

