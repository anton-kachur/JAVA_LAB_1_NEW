
    private static class App extends Creator
    {
        //агрегация
        MapCreator new_map;

        App(MapCreator b) {
            new_map = b;
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
