package stsjorbsmod.cards.wanderer;

import basemod.BaseMod;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.unique.LoseEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import stsjorbsmod.JorbsMod;
import stsjorbsmod.cards.CustomJorbsModCard;
import stsjorbsmod.characters.Wanderer;

import static stsjorbsmod.JorbsMod.makeCardPath;

public class TimeWalk extends CustomJorbsModCard {
    public static final String ID = JorbsMod.makeID(TimeWalk.class.getSimpleName());
    public static final String IMG = makeCardPath("Manipulation_Rares/time_walk.png");

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = Wanderer.Enums.WANDERER_GRAY_COLOR;

    private static final int COST = -1;
    private static final int ENERGY = 2;
    private static final int DRAW = 3;
    private static final int UPGRADE_PLUS_DRAW = 1;
    private static final int WEAK = 1;
    private static final int UPGRADE_PLUS_WEAK = -1;

    public TimeWalk() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = ENERGY;
        urMagicNumber = baseUrMagicNumber = DRAW;
        metaMagicNumber = baseMetaMagicNumber = WEAK;
        exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new LoseEnergyAction(EnergyPanel.getCurrentEnergy()));
        addToBot(new DiscardAction(p, p, BaseMod.MAX_HAND_SIZE, false));
        addToBot(new RemoveAllBlockAction(p, p));
        addToBot(new GainEnergyAction(magicNumber));
        addToBot(new DrawCardAction(p, urMagicNumber, false));
        if (metaMagicNumber > 0) {
            addToBot(new ApplyPowerAction(p, p, new WeakPower(p, metaMagicNumber, false)));
        }
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeUrMagicNumber(UPGRADE_PLUS_DRAW);
            upgradeMetaMagicNumber(UPGRADE_PLUS_WEAK);
            upgradeDescription();
        }
    }
}
