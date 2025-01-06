package core.Monsters;

import core.Manager.SpriteAnimation;

public class Ghost extends Monster {
    private Ghost(SpriteAnimation ghostSpriteAnimation) {
        super("Ghost", 100, 20, ghostSpriteAnimation);
    }

    public static Ghost creation() {
        //preset path
        String ghostPath = "file:/Users/hockjianteh/intellij turn-based-rpg/TurnBasedRPGEngine/Project/artwork/Ghost/character_ghost_idle.png";
        String ghostAttackPath = "file:/Users/hockjianteh/intellij turn-based-rpg/TurnBasedRPGEngine/Project/artwork/Ghost/character_ghost_attack.png";
        String ghostHurtPath = "file:/Users/hockjianteh/intellij turn-based-rpg/TurnBasedRPGEngine/Project/artwork/Ghost/character_ghost_hurt.png";
        String ghostDeathPath = "file:/Users/hockjianteh/intellij turn-based-rpg/TurnBasedRPGEngine/Project/artwork/Ghost/character_ghost_death.png";
        SpriteAnimation ghostSpriteAnimation = SpriteAnimation.of(ghostPath, ghostAttackPath, ghostHurtPath, ghostDeathPath,
//                0,0,
                64, 64, 3,13,
                false, false);
        return new Ghost(ghostSpriteAnimation);
    }


}

