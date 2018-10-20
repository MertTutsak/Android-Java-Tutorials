package com.example.mert.survivorbird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Gdx2DPixmap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;

import java.util.Random;

public class SurvivorBird extends ApplicationAdapter {

    private SpriteBatch batch;
    private Random random;

    //NESNELER
    private Texture textureBackground;
    private Texture textureBird;
    private Texture[] textureMonster;

    private float birdX = 0;
    private float birdY = 0;

    //Oyun Başladı mı ?
    private int gameState = 0;

    //Hız
    private float velocity = 0.0f;

    //Yer Çekimi
    private float gravity = 0.3f;

    //Force
    private float[] forceX;
    private final int forceCount = 4;
    private float forceVelocity = 2;
    private int forceDistance = 0;

    //Monster
    private float[][] monsterOffSet;
    private final int monsterCount = 3;

    //Circle
    private Circle circleBird;
    private Circle[][] circleMonster;

    //ShapeRenderer
    private ShapeRenderer shapeRenderer;

    //Score
    private int score = 0;
    private int scoredForce = 0;
    //Score Font
    private BitmapFont font;

    //END Font
    private BitmapFont font2;


    @Override
    public void create() {

        //Batch
        batch = new SpriteBatch();

        //Random
        random = new Random();

        //Background
        textureBackground = new Texture("background.png");

        //Bird
        textureBird = new Texture("bird.png");
        circleBird = new Circle();
        birdX = Gdx.graphics.getWidth() / 5;
        birdY = Gdx.graphics.getHeight() / 3;

        //Monster
        textureMonster = new Texture[monsterCount];
        monsterOffSet = new float[forceCount][monsterCount];
        circleMonster = new Circle[forceCount][monsterCount];
        for (int i = 0; i < monsterCount; i++) {
            textureMonster[i] = new Texture("monster.png");
            for (int j = 0; j < forceCount; j++) {
                monsterOffSet[j][i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - 200);
            }
        }

        //Force
        forceX = new float[forceCount];
        forceDistance = Gdx.graphics.getWidth() / 3;
        for (int i = 0; i < forceCount; i++) {
            forceX[i] = Gdx.graphics.getWidth() - (textureMonster[0].getWidth() / 2) + i * forceDistance;
        }

        //ShapeRenderer
        shapeRenderer = new ShapeRenderer();

        //Score Font
        font = new BitmapFont();
        font.setColor(Color.WHITE);
        font.getData().setScale(4);

        //Score Font2
        font2 = new BitmapFont();
        font2.setColor(Color.BLACK);
        font2.getData().setScale(6);
    }

    @Override
    public void render() {

        batch.begin();

        batch.draw(textureBackground, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        if (gameState == 0) {

            if (Gdx.input.justTouched()) {
                gameState = 1;
            }

        } else if (gameState == 1) {

            if (forceX[scoredForce] < 0) {
                score++;

                if (scoredForce < forceCount - 1) {
                    scoredForce++;
                } else {
                    scoredForce = 0;
                }
            }

            if (Gdx.input.justTouched()) {
                velocity = -7;
            }

            for (int i = 0; i < forceCount; i++) {

                if (forceX[i] < 0) {
                    forceX[i] += forceCount * forceDistance;

                    for (int j = 0; j < monsterCount; j++) {
                        monsterOffSet[i][j] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - 200);
                    }

                } else {
                    forceX[i] -= forceVelocity;
                }

                for (int j = 0; j < monsterCount; j++) {
                    batch.draw(textureMonster[j], forceX[i], (Gdx.graphics.getHeight() / 2) + monsterOffSet[i][j], Gdx.graphics.getWidth() / 15, Gdx.graphics.getHeight() / 10);

                    circleMonster[i][j] = new Circle(forceX[i] + (Gdx.graphics.getWidth() / 30), (Gdx.graphics.getHeight() / 2) + monsterOffSet[i][j] + (Gdx.graphics.getHeight() / 20), Gdx.graphics.getWidth() / 30);
                }
            }

            if (birdY > 0) {
                velocity += gravity;
                birdY -= velocity;
            } else {
                gameState = 2;
            }

            for (int i = 0; i < forceCount; i++) {
                for (int j = 0; j < monsterCount; j++) {
                    if (Intersector.overlaps(circleBird, circleMonster[i][j])) {
                        gameState = 2;
                    }
                }
            }

        } else if (gameState == 2) {

            font.draw(batch, "GAME OVER ! TAP TO PLAY AGAIN!", 175, Gdx.graphics.getHeight() / 2);
            if (Gdx.input.justTouched()) {
                gameState = 1;

                birdY = Gdx.graphics.getHeight() / 3;

                for (int i = 0; i < forceCount; i++) {
                    forceX[i] = Gdx.graphics.getWidth() - (textureMonster[0].getWidth() / 2) + i * forceDistance;

                    for (int j = 0; j < monsterCount; j++) {
                        monsterOffSet[i][j] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - 200);
                    }

                    velocity = 0;
                    score = 0;
                    scoredForce = 0;
                }
            }else {
                for (int i = 0; i < forceCount; i++) {
                    for (int j = 0; j < monsterCount; j++) {
                        batch.draw(textureMonster[j], forceX[i], (Gdx.graphics.getHeight() / 2) + monsterOffSet[i][j], Gdx.graphics.getWidth() / 15, Gdx.graphics.getHeight() / 10);
                    }

                    velocity = 0;
                    score = 0;
                    scoredForce = 0;
                }
            }
        }

        //For Bird
        batch.draw(textureBird, birdX, birdY, Gdx.graphics.getWidth() / 15, Gdx.graphics.getHeight() / 10);

        //Score FOont
        font.draw(batch, String.valueOf(score), 100, 200);

        batch.end();

        //For Bird
        circleBird.set(birdX + Gdx.graphics.getWidth() / 30, birdY + Gdx.graphics.getWidth() / 30, Gdx.graphics.getWidth() / 30);

        /*shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        //For Bird
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.circle(circleBird.x,circleBird.y,circleBird.radius);

        //For Monster
        for (int i = 0; i < forceCount; i++) {
            for (int j = 0; j < monsterCount; j++) {
                shapeRenderer.circle(forceX[i] + (Gdx.graphics.getWidth() / 30),(Gdx.graphics.getHeight() / 2) + monsterOffSet[i][j] + (Gdx.graphics.getHeight() / 20),Gdx.graphics.getWidth() / 30);
            }
        }

        shapeRenderer.end();*/
    }

    @Override
    public void dispose() {
    }
}
