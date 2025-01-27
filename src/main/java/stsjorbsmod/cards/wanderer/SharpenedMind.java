package stsjorbsmod.cards.wanderer;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import stsjorbsmod.JorbsMod;
import stsjorbsmod.cards.CustomJorbsModCard;
import stsjorbsmod.characters.Wanderer;
import stsjorbsmod.powers.SharpenedMindPower;

import static stsjorbsmod.JorbsMod.makeCardPath;
import static stsjorbsmod.characters.Wanderer.Enums.REMEMBER_MEMORY;

// [Power, unstackable]
// 1(0): At the start of your turn, remember Patience. At the end of your turn, remember Diligence.
public class SharpenedMind extends CustomJorbsModCard {
    public static final String ID = JorbsMod.makeID(SharpenedMind.class.getSimpleName());
    public static final String IMG = makeCardPath("Manipulation_Rares/sharpened_mind.png");

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;
    public static final CardColor COLOR = Wanderer.Enums.WANDERER_GRAY_COLOR;

    private static final int COST = 1;

    public SharpenedMind() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);

        tags.add(REMEMBER_MEMORY);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new SharpenedMindPower(p)));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            retain = true;
            upgradeDescription();
        }
    }
}
