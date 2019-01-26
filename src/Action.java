public class Action {

//    private String team;
//    private SendCommand actor;
//    private Memory memory;
//    private char side;

    private Action(SendCommand reactor, Memory memory, String team, char side) {
//        this.actor = reactor;
//        this.memory = memory;
//        this.side = side;
//        this.team = team;
    }

    public static void lookAround(SendCommand actor, Memory memory) {
        // If you don't know where is ball then find it
        actor.turn(40);
        memory.waitForNewInfo();
    }

    public static void dashTowardsBall(SendCommand actor, Memory memory, String team) {
        ObjectInfo ball = memory.getObject(Constants.BALL);
        PlayerInfo player = (PlayerInfo)memory.getObject(Constants.PLAYER);
        if (ball.direction != 0) {
            actor.turn(ball.direction);
        } else {
            if (!(player != null && player.getTeamName().equals(team) && player.distance <= 6)){
                actor.dash(10 * ball.distance);
            }
        }
    }

    public static void kickTowardsGoal(SendCommand actor, Memory memory, char side) {
        actor.kick(100, SoccerUtil.getOpponentsGoal(memory, side).direction);
    }

    public static void passBall(SendCommand actor, Memory memory) {
        PlayerInfo player = (PlayerInfo) memory.getObject(Constants.PLAYER);

        if (player != null){
            if (player.direction != 0) {
                actor.turn(player.direction);
            } else {
                actor.kick(5 * player.distance, player.direction);
            }
        } else {
            lookAround(actor, memory);
        }

    }

    public static void dashTowardsGoal(SendCommand actor, Memory memory, String team, char side){
        ObjectInfo goal = SoccerUtil.getOpponentsGoal(memory, side);
        ObjectInfo ball = memory.getObject(Constants.BALL);
        if (goal.direction != 0){
            actor.turn(goal.direction);
        } else {

            if (new PlayView(memory, team, side).hasBall()){
                actor.kick(20, goal.direction);
            } else if (ball != null){
                dashTowardsBall(actor,memory,team);
            }
        }
    }

    public enum Actions {
        DASH_TOWARDS_BALL,
        DASH_TOWARDS_GOAL,
        KICK_TOWARDS_GOAL,
        LOOK_AROUND,
        PASS_BALL,
        DO_NOTHING
    }

}
