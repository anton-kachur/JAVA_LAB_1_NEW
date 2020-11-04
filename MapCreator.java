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
