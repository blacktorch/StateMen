public abstract class States {

    protected States lookAround(Brain brain){
        Action.lookAround(brain.getReactor(), brain.getMemory());
        return this;
    }

    protected States pass(Brain brain){
        Action.passBall(brain.getReactor(), brain.getMemory());
        return this;
    }

    protected States shoot(Brain brain){
        Action.kickTowardsGoal(brain.getReactor(), brain.getMemory(), brain.getSide());
        return this;
    }

    protected States getBall(Brain brain){
        Action.dashTowardsBall(brain.getReactor(), brain.getMemory(), brain.getTeam());
        return this;
    }

    protected States dashTowardsGoal(Brain brain){
        Action.dashTowardsGoal(brain.getReactor(), brain.getMemory(), brain.getTeam(), brain.getSide());
        return this;
    }


    abstract String getStateName();
}
