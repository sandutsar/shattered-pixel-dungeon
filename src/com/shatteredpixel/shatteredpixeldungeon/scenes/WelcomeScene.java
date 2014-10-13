//TODO: update this class with relevant info as new versions come out.
package com.shatteredpixel.shatteredpixeldungeon.scenes;

import android.content.Intent;
import android.net.Uri;

import com.shatteredpixel.shatteredpixeldungeon.GamesInProgress;
import com.shatteredpixel.shatteredpixeldungeon.ShatteredPixelDungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroClass;
import com.shatteredpixel.shatteredpixeldungeon.ui.RedButton;
import com.watabou.noosa.BitmapTextMultiline;
import com.watabou.noosa.Camera;
import com.watabou.noosa.Game;
import com.shatteredpixel.shatteredpixeldungeon.ui.Archs;
import com.shatteredpixel.shatteredpixeldungeon.ui.Window;

public class WelcomeScene extends PixelScene {

    private static final String TTL_Welcome = "Welcome!";

    private static final String TTL_LastVer = "Updated To v0.2.1";

    private static final String TTL_Future = "Wait What?";

    private static final String TXT_Welcome =
            "Shattered Pixel Dungeon is a rework/expansion of Watabou's Pixel Dungeon.\n\n"+
            "The goal is to enhance the game by improving existing content and adding tonnes of new stuff!\n\n"+
            "Shattered Pixel Dungeon is being constantly updated, so expect more new content soon!\n\n"+
            "Happy Dungeoneering!";

    private static final String TXT_LastVer =
            "This update brings lots of bugfixes and significant changes to the sewers!\n\n" +
            "Expect some new quests from the ghost, improvements to the fight with Goo, and " +
            "much more useful early tips. The story is also being expanded on a little as well.\n\n" +
            "This update is mainly aimed at new players, but everyone should be able to appreciate some more " +
            "variety in the early stages of the game.";

    private static final String TXT_Future =
            "It seems that your current saves are from a future version of Shattered Pixel Dungeon.\n\n"+
            "You have either messing around with backup software, or something has gone buggy.\n\n"+
            "Regardless, tread with caution! Your saves may contain things which don't exist in this version, "+
            "this could cause some very weird errors to occur.";

    private static final String LNK = "https://play.google.com/store/apps/details?id=com.shatteredpixel.shatteredpixeldungeon";

    @Override
    public void create() {
        super.create();

        int gameversion = ShatteredPixelDungeon.version();

        BitmapTextMultiline text;
        BitmapTextMultiline title;

        if (gameversion == 0){

            text = createMultiline( TXT_Welcome, 8 );
            title = createMultiline( TTL_Welcome, 16 );

        } else if (gameversion <= Game.versionCode) {

            text = createMultiline(TXT_LastVer, 6 );
            title = createMultiline(TTL_LastVer, 12 );

        } else {

            text = createMultiline( TXT_Future, 8 );
            title = createMultiline( TTL_Future, 16 );

        }


        text.maxWidth = Math.min( Camera.main.width, 120 );
        text.measure();
        add( text );

        text.x = align( (Camera.main.width - text.width()) / 2 );
        text.y = align( (Camera.main.height - text.height()) / 2 );



        title.maxWidth = Math.min( Camera.main.width-50, 120 );
        title.measure();
        title.hardlight(Window.TITLE_COLOR);
        add( title );

        title.x = align( (Camera.main.width - title.width()) / 2 );
        title.y = align( text.y - title.height() - 10 );

        RedButton okay = new RedButton("Okay!") {
            @Override
            protected void onClick() {
                ShatteredPixelDungeon.version(Game.versionCode);
                Game.switchScene(TitleScene.class);
            }
        };

        okay.setRect(text.x, text.y + text.height() + 5, 55, 18);
        add(okay);

        RedButton revert = new RedButton("Changes") {
            @Override
            protected void onClick() {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(LNK));
                Game.instance.startActivity(intent);
            }
        };
        revert.setRect(text.x + 65, text.y + text.height() + 5, 55, 18);
        add(revert);

        Archs archs = new Archs();
        archs.setSize( Camera.main.width, Camera.main.height );
        addToBack( archs );

        fadeIn();
    }
}


