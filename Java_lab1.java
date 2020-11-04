import java.lang.String;
import java.util.*;
import java.lang.*;

//Anton Kachur TI-82
//Task: 7.Create a map using Java

public class Java_lab1 {

    static class MyException extends Exception{
        private int number;
        private int getNumber(){return number;}

        MyException() {}
        MyException(String message, int num){
            super(message);
            number = num;
        }
    }

    private static String [] road_types = {"Dirt", "Asphalt", "Paving stone"};
    private static String [] reservoir_types = {"Lake", "Sea", "Swamp", "River"};
    private static String [] locality_types = {"City", "Village", "Settlement"};
    private static String [] trees_types = {"Forest", "Steppe", "Forest-steppe"};
    private static String [] relief_types = {"Lowland", "Elevation", "Plateau", "Mountain", "Hollow"};
    private static Scanner input = new Scanner(System.in);


    //**************************Generates random name************************************************
    private static String generateName() {
        Random random = new Random();
        StringBuilder name = new StringBuilder("");

        for (int i=0; i<random.nextInt(50)+2; i++) {
            name.append(Character.toString((char)random.nextInt(25)+97));
        }

        System.out.println(name.toString());

        return name.toString().substring(0, 1).toUpperCase() + name.toString().substring(1);
    }


    //*************************************************************************************************
    //*******************All necessary classes*********************************************************
    //*************************************************************************************************
    private static class Road
    {
        private String road_type;
        private int number_of_lanes;

        private Road(String type, int num) {
            road_type = type;
            number_of_lanes = num;
        }

        private void printAll() { System.out.println("Road type: " + road_type + "\nNumber of lanes: " + number_of_lanes); }
    }


    private static class Reservoir
    {
        private String reservoir_type;
        private double depth;

        private Reservoir(String type, double d) {
            reservoir_type = type;
            depth = d;
        }

        private void printAll() {
            System.out.println("Reservoir type: " + reservoir_type + "\nDepth: " + depth);
        }
    }


    public static class Building
    {
        protected String name_of_building;
        protected String name_of_locality;
        protected int num_building;

        private Building(String name, String loc_name, int num) {
            name_of_building = name;
            name_of_locality = loc_name;
            num_building = num;
        }

        public void printAll() { System.out.println("Name of building: " + name_of_building + "\nName of locality: " + name_of_locality); }
    }


    public static class Locality extends Building
    {
        private String locality_name;
        private String locality_type;

        private Locality(String name, String type, int n) {

            super("Default name", name, n);

            locality_name = name;
            locality_type = type;
        }

        private String getName() { return locality_name; }

        public void printAll() {
            System.out.println("Name of locality: " + locality_name + "\nType of locality: " + locality_type);
        }

        public void printAll(boolean b) {
            if (b)
                System.out.println("Name of locality: " + locality_name + "\nType of locality: " + locality_type);
            else {
                System.out.println("Name of locality: " + locality_name + "\nType of locality: " + locality_type);
                System.out.println("Name of building in locality: " + name_of_building + num_building);
            }
        }
    }

    private static class Trees
    {
        private String name;
        private String type;

        private Trees(String name, String type) {
            this.name = name;
            this.type = type;
        }

        private void printAll() {
            System.out.println("Name: " + name + "\nType: " + type);
        }
    }


    //*************************************************************************************************
    //************************Class which contains map and its properties******************************
    //*************************************************************************************************
    private static class Map
    {
        //*************************************************************************************************
        //**********************Nested class, because each map has its own relief**************************
        //*************************************************************************************************
        private static class Relief
        {
            private String name;
            private int height;

            //*********Sets name of relief based on its height above the sea level***************************
            private Relief(int h, String name_rel) {
                name = name_rel;
                height = h;
                if (height > 0 && height < 200) name = relief_types[0];
                else if (height > 200 && height < 500) name = relief_types[1];
                else if (height > 500 && height < 2000) name = relief_types[2];
                else if (height > 2000) name = relief_types[3];
                else name = relief_types[4];
            }

            private void printAll() {
                System.out.println("Name of relief: " + name + "\nMeasure of relief: " + height);
            }

        }

        //***********************ArrayLists which contain map components**************************************
        private Scale scale;
        private String timezone;
        private ArrayList<Road> roads = new ArrayList<>();
        private ArrayList<Reservoir> reservoirs = new ArrayList<>();
        private ArrayList<Building> buildings = new ArrayList<>();
        private ArrayList<Locality> localities = new ArrayList<>();
        private ArrayList<Trees> trees = new ArrayList<>();
        private ArrayList<Relief> reliefs = new ArrayList<>();

        //*************Function, that represents the list of localities on alphabetical order*****************
        private void listOfLocalities() {
            if (localities.size() < 1) {
                System.out.println("\nThere is no localities\n");
            } else {
                Collections.sort(localities, Comparator.comparing(Locality::getName));
                System.out.println("All localities in alphabetic order: ");

                for (int i=0; i<localities.size(); i++)
                    System.out.println(localities.get(i).locality_name);
            }
        }

        private void findDuplicates() {
            if (localities.size() < 1) {
                System.out.println("\nThere is no localities\n");
            } else {
                for (int i=1; i<localities.size(); i++) {
                    if (localities.get(i-1).equals(localities.get(i))) {
                        System.out.println("There are duplicates: "+localities.get(i-1));
                    }
                }

            }
        }


        //**************Methods for setting map components and representing them*****************************
        private void printMap() {
            for (Road i : roads) i.printAll();
            for (Reservoir i : reservoirs) i.printAll();
            for (Building i : buildings) i.printAll();

            //With string concatenation with the other types
            for (Locality i : localities) i.printAll();
            //And not
            for (Locality i : localities) i.printAll(false);

            for (Trees i : trees) i.printAll();
            for (Relief i : reliefs) i.printAll();
            System.out.println("Map scale: "+scale+" Timezone: "+timezone);
        }
    }


    //*************************************************************************************************
    //***************Class which contains all functions for map generating*****************************
    //*************************************************************************************************
    interface SetOfMapMethods
    {
        void setLocalities(int n, boolean b) throws MyException;
        void setRoads(int n, boolean b) throws MyException;
        void setReservoirs(int n, boolean b) throws MyException;
        void setBuildings(int n, boolean b) throws MyException;
        void setTrees(int n, boolean b) throws MyException;
        void setRelief(int n, boolean b) throws MyException;
        void setScale(Scale s, int n);
    }


    //*************************************************************************************************
    //*****************************Class Builder extension*********************************************
    //*************************************************************************************************
    public static class MapCreator extends MyException implements SetOfMapMethods
    {
        private Map map;
        Random random;

        MapCreator () {
            map = new Map();
            random = new Random();
        }

        public void setRoads(int n, boolean b) throws MyException {
            if (n < 0) throw new MyException("The number is less than 1", n);
            for (int i=0; i<n; i++) {
                if (b) {
                    System.out.println("\nTypes of roads: ");
                    for (int j = 0; j<road_types.length; j++) {
                        System.out.println(j+") "+ road_types[j]);
                    }
                    System.out.println("\nEnter road type and number of lanes: ");
                    map.roads.add(new Road(road_types[input.nextInt()], input.nextInt()));

                }
                else
                    map.roads.add(new Road(road_types[random.nextInt(road_types.length)], random.nextInt(7)));
            }
        }

        public void setReservoirs(int n, boolean b) throws MyException {
            if (n < 0) throw new MyException("The number is less than 1", n);
            for (int i=0; i<n; i++) {
                if (b) {
                    System.out.println("\nTypes of reservoirs: ");
                    for (int j = 0; j<reservoir_types.length; j++) {
                        System.out.println(j+") "+ reservoir_types[j]);
                    }
                    System.out.println("\nEnter reservoir type and its depth: ");
                    map.reservoirs.add(new Reservoir(reservoir_types[input.nextInt()], input.nextDouble()));

                }
                else
                    map.reservoirs.add(new Reservoir(reservoir_types[random.nextInt(reservoir_types.length)], random.nextInt(1000)));
            }
        }
        public void setBuildings(int n, boolean b) throws MyException {
            if (n < 0) throw new MyException("The number is less than 1", n);
            for (int i=0; i<n; i++) {
                if (b) {
                    System.out.println("\nEnter name of building and name of locality: ");
                    map.buildings.add(new Building(input.nextLine(), input.nextLine(), random.nextInt(50)));
                }
                else
                    map.buildings.add(new Building(generateName(), locality_types[random.nextInt(locality_types.length)], random.nextInt(50)));
            }
        }
        public void setLocalities(int n, boolean b) throws MyException {
            if (n < 0) throw new MyException("The number is less than 1", n);
            for (int i=0; i<n; i++) {
                if (b) {
                    System.out.println("\nEnter name and type of locality: ");
                    map.localities.add(new Locality(input.nextLine(), input.nextLine(), random.nextInt(50)));
                }
                else
                    map.localities.add(new Locality(generateName(), locality_types[random.nextInt(locality_types.length)], random.nextInt(50)));
            }
        }
        public void setTrees(int n, boolean b) throws MyException {
            if (n < 0) throw new MyException("The number is less than 1", n);
            for (int i=0; i<n; i++) {
                if (b) {
                    System.out.println("\nEnter name and type of trees: ");
                    map.trees.add(new Trees(input.nextLine(), input.nextLine()));
                }
                else
                    map.trees.add(new Trees("Something", trees_types[random.nextInt(trees_types.length)]));
            }
        }
        public void setRelief(int n, boolean b) throws MyException {
            if (n < 0) throw new MyException("The number is less than 1", n);
            for (int i=0; i<n; i++) {
                if (b) {
                    System.out.println("\nEnter name and height of relief: ");
                    map.reliefs.add(new Map.Relief(input.nextInt(), input.nextLine()));
                }
                else
                    map.reliefs.add(new Map.Relief(random.nextInt(5000), "Something"));
            }
        }
        public void setScale(Scale s, int n) {
            map.scale = s;
            map.timezone = map.scale.setTimeZone(n);
        }

        private Map resultMap() { return map; }
    }




    public static abstract class Creator
    {
        Random random = new Random();

        protected abstract void createMap(MapCreator builder);
        protected abstract void createMap(MapCreator builder, Scale user_scale);

    }



    private static class App extends Creator
    {
        MapCreator new_map;

        App(MapCreator b) {
            new_map = new MapCreator();
        }

        private void makeMap(int choice)
        {

            switch(choice) {
                case 1: createMap(new_map); break;
                case 2:
                    System.out.println("Scale types: ");
                    for(Scale item: Scale.values())
                        System.out.println(item.ordinal() + ") " + item);

                    System.out.println("Enter map scale: ");
                    while (true) {
                        String c = "";
                        try {
                            c = input.nextLine();
                        } catch (Exception e) {
                            System.out.println("Wrong input");
                            c = Scale.SCALE_X1.toString();
                        }
                        finally {
                            for (Scale item : Scale.values())
                                if (item.toString().equals(c)) {
                                    System.out.println("Enter time zone: ");

                                    createMap(new_map, item);
                                }
                        }
                    }

            }

            Map map = new_map.resultMap();
            map.printMap();
            map.listOfLocalities();
            map.findDuplicates();
        }

        protected void createMap(MapCreator build)
        {
            try {
                build.setScale(Scale.SCALE_X1, 1);
                build.setRoads(random.nextInt(10), false);
                build.setReservoirs(random.nextInt(10), false);
                build.setBuildings(random.nextInt(10), false);
                build.setLocalities(random.nextInt(10), false);
                build.setTrees(random.nextInt(10), false);
                build.setRelief(random.nextInt(10), false);
            }catch (MyException ex) {
                System.out.println(ex.getMessage());
                System.out.println(ex.getNumber());
            }
        }

        //********************Creates map****************************************
        protected void createMap(MapCreator build, Scale user_scale)
        {
            try {
                System.out.println("Enter the time zone: ");
                build.setScale(user_scale, input.nextInt());
                System.out.println("Enter the number of roads: ");
                build.setRoads(input.nextInt(), true);
                System.out.println("Enter the number of reservoirs: ");
                build.setReservoirs(input.nextInt(), true);
                System.out.println("Enter the number of buildings: ");
                build.setBuildings(input.nextInt(), true);
                System.out.println("Enter the number of localities: ");
                build.setLocalities(input.nextInt(), true);
                System.out.println("Enter the number of trees: ");
                build.setTrees(input.nextInt(), true);
                System.out.println("Enter the number of relief: ");
                build.setRelief(input.nextInt(), true);
            }catch (MyException ex) {
                System.out.println(ex.getMessage());
                System.out.println(ex.getNumber());
            }
        }

    }

    public enum Scale {
        SCALE_X1 {
            public String toString() {
                return "Default";
            }
        },
        SCALE_X2 {
            public String toString() {
                return "x2";
            }
        },
        SCALE_X3 {
            public String toString() {
                return "x3";
            }
        },
        SCALE_X5 {
            public String toString() {
                return "x5";
            }
        },
        SCALE_X10 {
            public String toString() {
                return "x10";
            }
        },
        SCALE_X100 {
            public String toString() { return "x100"; }
        };

        public String setTimeZone(int n) {
            return (n>0)? ("Timezone: +" + n): ("Timezone: "+n);
        }
    }

    public static void main (String []args)
    {
        MapCreator new_map = new MapCreator();
        App app = new App(new_map);
        System.out.println("What kind of map do you want?\n1->Random map;\n2->Custom map;\nYour choice: ");

        switch (input.nextInt()) {
            case 1: app.makeMap(1); break;
            case 2: app.makeMap(2); break;
            default: System.out.println("Invalid input"); break;
        }

    }
}
