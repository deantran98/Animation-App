package cs3500.animator.view;

import cs3500.animator.controller.ViewListenerAdvance;
import cs3500.animator.model.IAnimatorApp;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 * Class represent an interactive view. The view will take in events from keyboard, button, etc. and
 * notify these events to the controller, so the controller can respond.
 */
public class InteractiveView extends VisualAnimationView implements AnimatorAdvanceView,
    ActionListener, KeyListener {

  private final List<ViewListenerAdvance> listeners;
  private final Map<Character, Runnable> keyMap;

  /**
   * Constructor of the interactive view.
   */
  public InteractiveView(IAnimatorApp model, int tickPerSec) {
    super(model, tickPerSec);
    this.listeners = new ArrayList<>();
    keyMap = new HashMap<>();
    initKeys();

    JButton restartAnimation = new JButton("Enable/Disable Looping");
    restartAnimation.setActionCommand("loop");
    JButton increaseSpeed = new JButton("Speed up");
    increaseSpeed.setActionCommand("increase");
    JButton decreaseSpeed = new JButton("Slow down");
    decreaseSpeed.setActionCommand("decrease");
    JLabel label = new JLabel("New feature: Hit 'd' key to play discrete time. Old features: "
        + "Hit 's' key to start/resume. Hit 'p' key to stop. Hit 'r' key to restart");

    restartAnimation.addActionListener(this);
    increaseSpeed.addActionListener(this);
    decreaseSpeed.addActionListener(this);
    this.addKeyListener(this);

    add(restartAnimation, BorderLayout.PAGE_END);
    add(increaseSpeed, BorderLayout.EAST);
    add(decreaseSpeed, BorderLayout.WEST);
    add(label, BorderLayout.PAGE_START);
  }

  @Override
  public void addListener(ViewListenerAdvance listener) {
    if (listener == null) {
      throw new IllegalArgumentException("Listener can't be null.");
    }
    this.listeners.add(listener);

  }

  @Override
  public void resetFocus() {
    setFocusable(true);
    requestFocus();
  }

  @Override
  public void displayMessage(String message) {
    JFrame frame = new JFrame();
    JOptionPane.showMessageDialog(frame, message);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    //hit restart button
    if (e.getActionCommand().equals("loop")) {
      for (ViewListenerAdvance listener : listeners) {
        listener.triggerLooping();
      }
    }

    //hit increase speed button
    if (e.getActionCommand().equals("increase")) {
      for (ViewListenerAdvance listener : listeners) {
        listener.increaseSpeed();
      }
    }

    //hit decrease speed button
    if (e.getActionCommand().equals("decrease")) {
      for (ViewListenerAdvance listener : listeners) {
        listener.decreaseSpeed();
      }
    }

  }

  void initKeys() {

    //hit s key to start/resume animation
    keyMap.putIfAbsent('s', new Runnable() {
      @Override
      public void run() {
        for (ViewListenerAdvance listener : listeners) {
          listener.getStartOrResume();
        }
      }
    });

    //hit p key to pause animation
    keyMap.putIfAbsent('p', new Runnable() {
      @Override
      public void run() {
        for (ViewListenerAdvance listener : listeners) {
          listener.pauseAnimation();
        }
      }
    });

    //hit r key to restart animation
    keyMap.putIfAbsent('r', new Runnable() {
      @Override
      public void run() {
        for (ViewListenerAdvance listener : listeners) {
          listener.restartAnimation();
        }
      }
    });

    //hit d key to play discrete time animation
    keyMap.putIfAbsent('d', new Runnable() {
      @Override
      public void run() {
        for (ViewListenerAdvance listener : listeners) {
          listener.triggerDiscreteTime();
        }
      }
    });
  }

  @Override
  public void keyTyped(KeyEvent e) {
    Runnable runnable = keyMap.getOrDefault(e.getKeyChar(), null);
    if (runnable != null) {
      runnable.run();
    }

  }

  @Override
  public void keyPressed(KeyEvent e) {
    //My view doesn't need to listen to any key pressed
  }

  @Override
  public void keyReleased(KeyEvent e) {
    //My view doesn't need to listen to any key released
  }
}
