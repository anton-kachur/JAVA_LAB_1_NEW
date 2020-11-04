public static abstract class Creator
    {
        Random random = new Random();

        protected abstract void createMap(MapCreator builder);
        protected abstract void createMap(MapCreator builder, Scale user_scale);

    }
