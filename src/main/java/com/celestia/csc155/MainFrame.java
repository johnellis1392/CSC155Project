
package com.celestia.csc155;

import com.celestia.csc155.input.InputHandler;
import com.celestia.csc155.util.GLEventHandler;
import com.celestia.csc155.util.R;

import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.KeyListener;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;

import javax.swing.*;

public class MainFrame extends JFrame {

    private final double aspectRatio = R.util.aspect;
    private final int width = R.util.width;
    private final int height = R.util.height;
    private final int fps = R.util.fps;
    private final Dimension size = new Dimension(width, height);

//    private final InputHandler inputHandler = new InputHandler();
    private final GLEventListener glEventHandler = new GLEventHandler();
    private final GLCanvas glCanvas = new GLCanvas();
    private final FPSAnimator fpsAnimator = new FPSAnimator(glCanvas, fps);

    public MainFrame() {
        setTitle(R.util.title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().add(glCanvas);
//        glEventHandler.setInputHandler(inputHandler);
        glCanvas.addGLEventListener(glEventHandler);
//        glCanvas.addMouseListener((MouseListener) inputHandler);
//        glCanvas.addKeyListener((KeyListener) inputHandler);
        glCanvas.setAnimator(fpsAnimator);
        setSize(size);
        setLocationRelativeTo(null);
        //setResizable(false);
        setVisible(true);
        fpsAnimator.start();
    }
}
