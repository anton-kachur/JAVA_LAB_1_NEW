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

    
        public boolean equals(Map obj) {
            return (obj == null);
        }

    
        //**************Methods for setting map components and representing them*****************************
        private void printMap() {
            if (equals(this))
                System.out.println("Map is empty!");
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
