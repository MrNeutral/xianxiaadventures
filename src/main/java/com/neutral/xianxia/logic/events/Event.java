/*
 * Copyright (C) 2019 Mr.Neutral
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.neutral.xianxia.logic.events;

/**
 *
 * @author Mr.Neutral
 */
public enum Event {
    //<editor-fold defaultstate="collapsed" desc="Events">
    SECT_MEMBER_DIES(0.2, -300, "While you were exploring a member of the Immortal Sect you were close to died.\nYou cough blood in grief.", "Sect Member Dies"),
    SECT_DESTOYED(0, -1000, "While you were exploring, the Immortal Sect got destroyed by the Demon Sect.\nYou cough out blood in helplessness.", "Sect Destroyed"),
    JOIN_SECT(0.4, 50, "While you were exploring, you met a mysterious cultivator. After debating with him about the dao, he revealed himself to be a member of the Immortal Sect and invited you to join.", "Sect Joined"),
    BITTEN_BY_PET(0.5, -50, "Your mystical beast bit you and you cough blood.", "Bitten by pet"),
    PET_FINDS_TREASURE(0.7, 100, "Your mystical beast found a mystical treasure while you were wandering.", "Pet finds treasure"),
    GOT_PET(0.8, 100, "During your travels you managed to tame a mystical beast and make it your pet.", "Tamed beast"),
    PET_DIED(0.2, -200, "Your mystical beast died for unknown reasons, you cough blood out of sadness and anger.", "Pet died"),
    PET_KILLED(0.2, -250, "Your mystical beast got killed by someone while you weren't with it.\nYou cough blood.", "Pet killed"),
    PET_POISONED(0.3, -275, "You fed your mystical beast something and it died, you cough blood in anger and grief.", "Pet poisoned"),
    PET_FOUND_MATE(0.4, -150, "Your mystical beast found their special someone and ran away.\nYou cough blood in anger.", "Pet left"),
    PET_STOLEN(0.3, -150, "Your mystical beast got stolen.\nYou try your best to find it, but you fail.\nIn the end you cough blood and faint.", "Pet stolen"),
    PET_ATE_TREASURE(0.3, -200, "Your pet accidentally swallowed your life-saving artifact.\nIt survived and now uses puppy-eyes on you.\nYou cough blood in anger.", "Pet eats treasure"),
    FAILED_TO_TAME_PET(0.3, -200, "You failed in taming the mystical beast you always wanted.\nIt now hates you.\nYou cough blood.", "Mystical beast hates you"),
    PET_TAMED_BY_OTHER(0.3, -200, "You tried to tame your favourite mystical beast, but someone was faster.\nYou cough blood in anger.", "Mystical beasted tamed by other cultivator"),
    PILL_COUGH_BLOOD(0.2, -50, "Your body showed a strange reaction to a pill you just consumed.\nYou cough blood.", "Strange pill"),
    PILL_IMPURE_CURE(0.4, -300, "You buy some medicine from a shady dealer.\nYou are desperate to cure your wounds...\nThe medicine is really impure and your wounds get worse, you cough some blood in the aftermath.", "Impure medicine"),
    PILL_FAINT(0.5, -200, "You are consuming the pill you just concocted when you faint.\nAfter waking up you cough blood.", "Impure pill"),
    PILL_SHRINK(0.1, -500, "You are consuming the pill you just created when you shrink yourself to half your height and faint.\nWhen you wake up you cough some blood and hope that this isn't permanent.", "Shrinking pill"),
    PILL_GROW(0.1, -500, "You are consuming the pill you just created when you grow to double your height.\nYour body is exhausted and you faint.\nWhen you wake up you cough some blood and hope that this isn't permanent.", "Growing pill"),
    PILL_BUNNY(0, -700, "You bought an unknown pill from a renowned pill maker and you fainted after consuming it.\nWhen you woke up, you realised that you had grown a pair of bunny ears and tail.\nYou cough blood in humiliation.", "Bunny pill"),
    PILL_DREAM(0.4, -100, "You accidentally dig up a unknown pill.\nTo eat or not to eat...\nIn the end you eat it .\nAfter this everything goes in your favour till you are the strongest being in existence.\nYou suddenly wake up and realize this was only a dream.\nYou are busy coughing blood for the next few days.", "Dream pill"),
    GAMBLING_FAIL(0.4, -100, "You fail at gambling and cough blood.", "Failed gambling"),
    CASINO_CHEAT_FOUND(0.3, -200, "You are found cheating in a casino and are beaten till you cough blood.", "Found cheating"),
    ASSASSIN_SNEAK_ATTACK(0.2, -400, "You got sneaked attacked by a assassin.\nAlthough he didn't manage to kill you he managed to injure you.\nYou cough blood.", "Assassin attack"),
    CUSTOMER_MOB(0.5, -50, "On the way back to the Immortal Sect, you got mobbed by angry customers claiming that you sold them defective pills.\nThey beat you up so bad that you cough blood.", "Customer mob"),
    SLAVES_STEAL_MONEY(0.2, -200, "Your slaves had been stealing your Spirit Stones for years and you hadn't realized it.\nYou beat them to death but the Spirit Stones are gone.\nYou cough blood.", "Slaves' theft revealed"),
    SHOP_ROBBED(0.3, -250, "Your shop was robbed.\nYou cough blood in anger.", "Shop robbed"),
    SHOPKEEPER_STEALS_EVERYTHING(0.2, -350, "Your trusted shopkeeper stole all your artifacts and ran away...\nEven the floor tiles werent spared...\nYou cough blood in anger.", "Shopkeeper steals everything"),
    CHOSEN_KILLS_GRANDSON(0.1, -500, "The Chosen One killed your only grandson infront of your eyes.\nThe only thing you can come up with is: \"Junior you dare!\", so you shout it as loud as you can.\nBig mistake.\nThe Chosen One beats you up.\nYou cough blood before finally fainting.", "Chosen One kills grandson"),
    CHOSEN_DESTROYS_PAVILION(0.1, -500, "While cultivating in seclusion your sect was infiltrated by the Chosen One, his pill concocting destroyed the Immortal Sect's Pavilion of Techniques just as you were about to get your secret technique.\nYou cough blood in anger.", "Chosen One destroys pavillion"),
    PROMISSORY_NOTE(0.2, -400, "Ah! You were forced to write a promissory note.\nAll your Spirit Stones down the drain.\nYou cough blood in humiliation and swear vengeance.", "Promissory note"),
    SLIP_BANANA(0, -600, "You slipped on a banana peel and cough blood from the humiliation.", "Banana slip"),
    HOT_HOTPOT(0, -600, "You went to eat mala hotpot but you didn't realize that the peppers they were serving were actually the hottest peppers in the world.\nAll the pores in your body are burning and you were hospitalised for 6 months all the while you were coughing blood", "Hot hotpot"),
    CRASH_BOAT(0, -600, "You rented an expensive boat thinking that you could drive it with your Qi, but you destroyed it by accident and ended had to compensate the owner for the loss and mental distress.\nYou cough blood from the humiliation.", "Boat crashed"),
    DRAGON_IN_CAVE(0.1, -400, "While you were adventuring, a demonic dragon occupied your Immortal Cave.\nYou try to fight it but it's too strong for you, so you can only run away in defeat while coughing blood.", "Demonic dragon in Immortal Cave"),
    CAVE_DESTROYED(0, -800, "While you were adventuring, someone destroyed the whole entire area around your Immortal Cave...\nEverything is gone and you faint while coughing blood from anger.", "Immortal Cave destroyed"),
    CAVE_ROBBED(0.3, -300, "Your treasures in your Immortal Cave were robbed.\nYou cough blood out of anger.", "Immortal Cave Robbed"),
    CAVE_STOLEN(0.2, -350, "Someone stronger than you stole your Immortal Cave on the best cultivation spot in the mountain and chased you away.\nHumiliated, you cough blood.", "Immortal Cave stolen"),
    INHERITANCE_CLAIMED(0.6, -75, "You discovered the inheritance of an Ancient Cultivator and reached the final stage only to discover it had been claimed long ago.\nAs soon as you realize this, your body automatically coughs up blood.", "Inheritance already claimed"),
    DISCIPLE_BETRAYS(0.3, -75, "Your favourite disciple betrayes you.\nHe sold your cultivation manuals to another cultivator and ran away.\nYou are so angered that you cough blood.", "Betrayed by disciple"),
    SYSTEM_DESTROYED(0, -1000, "You accidentally destroyed your Qi System.\nIt will take you a long time to recover.\nYou cough blood from this shameful event.", "Qi System destroyed"),
    CAULDRON_EXPLODES(0.2, -250, "You tried your luck at alchemy.\nThe cauldron exploded and critically injured you.", "Cauldron explodes"),
    PILL_RUINED(0.5, -100, "You tried to concoct a pill.\nYou fucked up identifying one ingredient and ruined it.\nYou cough blood in anger.", "Pill ruined"),
    NEIGHBOR_BBQ(0, -450, "Your neighboring cultivator, who has an unusual love for peppers, invites you to a BBQ.\nYou are full of hopes and expect greatness.\nIt tastes terrible and you dont dare to complain, since he isn't good with criticism.\nSo you endure it and when you return home, you cough blood and faint.", "BBQ"),
    AUCTION_BEAT_UP(0.2, -250, "You ended up bidding at an auction and managed to win item you wanted, but little did you know, the Chosen One wanted the item too.\nHe beat you up and stole the item.\nYou cough blood in humiliation.", "Auction item stolen"),
    PRAY_EXP_SUCCESS(0, 2000, "You sit down and pray to the God of Exp.\nThe prayer intrigues the God and he grants you divine knowledge.", "Prayer to God of Exp"),
    PRAY_EXP_FAILURE(0.1, -2000, "You sit down and lazily pray to the God of Exp.\nHe is angered by this and has you struck by Heavenly Tribulation.", "Lazy Prayer to God of Exp"),
    EASY_BATTLE(0.3, 0, "A junior cultivator challenges you to a battle, this should be easy.", "Easy Battle"),
    AVERAGE_BATTLE(0.7, 0, "A fellow cultivator challenges you to a battle, this should help you improve your skills.", "Average Battle"),
    HARD_BATTLE(0.1, 0, "A senior challenges you to a battle losing face. Why would he do this ?", "Hard Battle");
    //</editor-fold>
    private final double rarity;

    private final int expEffect;
    private final String eventText;
    private final String eventName;

    private Event(double rarity, int expEffect, String eventText, String eventName) {
        this.rarity = rarity;
        this.expEffect = expEffect;
        this.eventText = eventText;
        this.eventName = eventName;
    }

    /**
     * @return the rarity
     */
    public double getRarity() {
        return rarity;
    }

    /**
     * @return the expEffect
     */
    public int getExpEffect() {
        return expEffect;
    }

    /**
     * @return the eventText
     */
    public String getEventText() {
        return eventText;
    }

    /**
     * @return the eventName
     */
    public String getEventName() {
        return eventName;
    }
}
