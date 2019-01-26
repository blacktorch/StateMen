
public class Behavior {

    private PlayView.Environments environments;
    private Action.Actions action;
    private States state;
    private Brain brain;

    public Behavior(States state, PlayView.Environments environments, Action.Actions action){
        this.state = state;
        this.environments = environments;
        this.action = action;
    }

    public PlayView.Environments getEnvironments() {
        return environments;
    }

    public void setEnvironments(PlayView.Environments environments) {
        this.environments = environments;
    }

    public Action.Actions getAction() {
        return action;
    }

    public void setAction(Action.Actions action) {
        this.action = action;
    }

    public boolean isInCurrentState(Brain brain){
        this.brain = brain;
        PlayView playView = new PlayView(brain.getMemory(), brain.getTeam(), brain.getSide());

        boolean[] environs = new boolean[2];

        if (state.getStateName().equals(Constants.HAS_BALL) && playView.hasBall()){
            environs[0] = true;
            switch (environments){
                case CAN_SEE_BALL:
                    environs[1] = playView.canSeeBall();
                    break;
                case FAR_FROM_GOAL:
                    environs[1] = playView.farFromGoal();
                    break;
                case BALL_NOT_VISIBLE:
                    environs[1] = !playView.canSeeBall();
                    break;
                case CAN_SEE_GOAL:
                    environs[1] = playView.canSeeGoal();
                    break;
                case CAN_SEE_TEAM_MATE:
                    environs[1] = playView.canSeeTeamMate();
                    break;
                case TEAM_MATE_HAS_BALL:
                    environs[1] = playView.teamMateHasBall();
                    break;
                case FACING_MY_GOAL:
                    environs[1] = playView.facingMyGoal();
                    break;
                case GOAL_NOT_VISIBLE:
                    environs[1] = !playView.canSeeGoal();
                    break;
            }

        } else if (state.getStateName().equals(Constants.NOT_WITH_BALL) && !playView.hasBall()){
            environs[0] = true;
            switch (environments){
                case CAN_SEE_BALL:
                    environs[1] = playView.canSeeBall();
                    break;
                case FAR_FROM_GOAL:
                    environs[1] = playView.farFromGoal();
                    break;
                case BALL_NOT_VISIBLE:
                    environs[1] = !playView.canSeeBall();
                    break;
                case CAN_SEE_GOAL:
                    environs[1] = playView.canSeeGoal();
                    break;
                case CAN_SEE_TEAM_MATE:
                    environs[1] = playView.canSeeTeamMate();
                    break;
                case TEAM_MATE_HAS_BALL:
                    environs[1] = playView.teamMateHasBall();
                    break;
                case FACING_MY_GOAL:
                    environs[1] = playView.facingMyGoal();
                    break;
                case GOAL_NOT_VISIBLE:
                    environs[1] = !playView.canSeeGoal();
                    break;
            }

        }

        return SoccerUtil.areAllTrue(environs);
    }

    public void performAction(){

        switch (action){
            case PASS_BALL:
                brain.setAgentState(state.pass(brain));
                break;
            case LOOK_AROUND:
                brain.setAgentState(state.lookAround(brain));
                break;
            case DASH_TOWARDS_BALL:
                brain.setAgentState(state.getBall(brain));
                break;
            case DASH_TOWARDS_GOAL:
                brain.setAgentState(state.dashTowardsGoal(brain));
                break;
            case KICK_TOWARDS_GOAL:
                brain.setAgentState(state.shoot(brain));
                break;
            case DO_NOTHING:
                break;
        }

        Logger.logRun(brain, action);

    }
}
