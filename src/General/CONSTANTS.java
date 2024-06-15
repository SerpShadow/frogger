package General;

public interface CONSTANTS {
    int CHUNK_SIZE = 16;
    int CHUNK_SIZE_HALF = CHUNK_SIZE / 2;
    int CHUNKS_HORIZONTAL = 14;
    int CHUNKS_VERTICAL = 16;
    int PIXEL_HORIZONTAL = CHUNKS_HORIZONTAL * CHUNK_SIZE;
    int PIXEL_VERTICAL = CHUNKS_VERTICAL * CHUNK_SIZE;
    int SPEED_FROG = 4;
    int POINTS_PER_STEP = 10;
    int POINTS_PER_FROG = 50;
    int POINTS_PER_LEVEL = 1000;
    int POINTS_PER_SECOND = 10;
    int RESPAWN_DELAY = 2000; // milisec
    LevelData[] LEVEL_LIST = new LevelData[]{
            new LevelData(
                    3,
                    1,

                    5,
                    -1,

                    2,
                    2,

                    3,
                    0.5,

                    2,
                    -1,

                    0,
                    0,

                    2,
                    -1,

                    1,
                    0.75,

                    3,
                    -0.5,

                    2,
                    0.5,

                    3,
                    -0.5),
            new LevelData(
                    3,
                    1,

                    4,
                    -1,

                    1,
                    2,

                    3,
                    0.5,

                    3,
                    -1,

                    0,
                    0,

                    3,
                    -1,

                    2,
                    1,

                    4,
                    -0.5,

                    3,
                    0.5,

                    4,
                    -0.5),
            new LevelData(
                    2,
                    1.5,

                    3,
                    -1,

                    1,
                    1.5,

                    3,
                    0.5,

                    3,
                    -2,

                    0,
                    0,

                    3,
                    -1,

                    2,
                    4,

                    4,
                    -1,

                    4,
                    1,

                    3,
                    -1),
            new LevelData(
                    2,
                    1.5,

                    3,
                    -1,

                    1,
                    1.5,

                    2,
                    0.5,

                    3,
                    -2,

                    1,
                    1.5,

                    4,
                    -1,

                    3,
                    4,

                    4,
                    -1,

                    3,
                    1,

                    4,
                    -1),
            new LevelData(
                    1,
                    1.5,

                    3,
                    -1,

                    1,
                    1.5,

                    2,
                    0.5,

                    2,
                    -2,

                    2,
                    1.5,

                    3,
                    -1,

                    4,
                    4,

                    5,
                    -1,

                    4,
                    1,

                    5,
                    -1)
    };
}
