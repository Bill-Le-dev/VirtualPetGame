import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.MalformedURLException;

public class GUI extends JFrame implements ActionListener {
    private Clip clip;
    private JLabel nameLabel;
    private JLabel hungerLabel;
    private JLabel happinessLabel;
    private JLabel energyLabel;
    private JButton feedButton;
    private JButton walkButton;
    private JButton sleepButton;
    private JButton status;
    private JButton save;
    private JButton load;

    private ImageIcon idle;
    private ImageIcon sleep;
    private ImageIcon eat;
    private ImageIcon walk;
    private JLabel image;
    private JLabel empty;

    private Dog dog = new Dog(50, 50, 50, "");

    public GUI() {
        String name = JOptionPane.showInputDialog(null, "What is the name of your dog?");
        dog.setName(name);
        nameLabel = new JLabel("Name: " + name);
        hungerLabel = new JLabel("                 Hunger lvl: " + dog.getHunger_lvl());
        happinessLabel = new JLabel("Happiness lvl: " + dog.getHappiness_lvl());
        energyLabel = new JLabel("Energy lvl: " + dog.getEnergy_lvl());
        feedButton = new JButton("FEED");
        empty = new JLabel();
        walkButton = new JButton("WALK");
        sleepButton = new JButton("SLEEP");
        save = new JButton("SAVE");
        load = new JButton("LOAD");
        status = new JButton("STATUS: "+dog.getMood());
        idle = new ImageIcon("HuskyIdle.jpg");
        sleep = new ImageIcon("HuskySleep.jpg");
        eat = new ImageIcon("HuskyEat.jpg");
        walk = new ImageIcon("HuskyWalk.jpg");

        image = new JLabel(idle);
        image.setPreferredSize(new Dimension(1000,1000));

        feedButton.addActionListener(this);
        walkButton.addActionListener(this);
        sleepButton.addActionListener(this);
        sleepButton.addActionListener(this);
        save.addActionListener(this);
        load.addActionListener(this);

        setLayout(new GridLayout(2, 0));

        add(nameLabel);
        add(empty);
        add(image);
        add(hungerLabel);
        add(happinessLabel);
        add(energyLabel);
        add(save);
        add(load);

        setTitle("MY VIRTUAL PET");
        setBounds(0, 0, 1000, 1000);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        pack();

        add(feedButton);
        add(walkButton);
        add(sleepButton);
        add(status);
    }

    public void actionPerformed(ActionEvent e) {
        if (dog != null) {
            if (e.getSource() == save)
            {
                ObjectOutputStream outputStream = null;
                try {
                    outputStream = new ObjectOutputStream(new FileOutputStream("MyPetData.dat"));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    outputStream.writeObject(dog);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
            if (e.getSource() == load)
            {
                Dog loadedDog = null;
                try {
                    ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("MyPetData.dat"));
                    loadedDog = (Dog) inputStream.readObject();
                    dog = loadedDog;
                    nameLabel.setText("Name: "+dog.getName());
                    hungerLabel.setText("                 Hunger lvl: " + dog.getHunger_lvl());
                    happinessLabel.setText("Happiness lvl: " + dog.getHappiness_lvl());
                    energyLabel.setText("Energy lvl: " + dog.getEnergy_lvl());
                    status.setText("Status: "+dog.getMood());
                } catch (IOException | ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
            if (e.getSource() == feedButton)
            {
                dog.feed();
                hungerLabel.setText("                 Hunger lvl: " + dog.getHunger_lvl());
                image.setIcon(eat);
            }

            if (e.getSource() == walkButton) {
                dog.walk();
                happinessLabel.setText("Happiness lvl: " + dog.getHappiness_lvl());
                image.setIcon(walk);
            }

            if (e.getSource() == sleepButton) {
                dog.sleep();
                energyLabel.setText("Energy lvl: " + dog.getEnergy_lvl());
                image.setIcon(sleep);
            }

            String fileName;

            if (dog.getHunger_lvl() == 0)
            {
                dog.setMood("Hungry");
                fileName = "Feed.wav";
                try {
                    File file = new File(fileName);
                    if (file.exists()) {
                        clip = AudioSystem.getClip();
                        AudioInputStream sound = AudioSystem.getAudioInputStream(file.toURI().toURL());
                        clip.open(sound);
                    } else {
                        throw new RuntimeException("Sound: file not found: " + fileName);
                    }
                } catch (MalformedURLException exception) {
                    throw new RuntimeException("Sound: Malformed URL: " + exception);
                } catch (UnsupportedAudioFileException exception) {
                    throw new RuntimeException("Sound: Unsupported Audio File: " + exception);
                } catch (IOException exception) {
                    throw new RuntimeException("Sound: Input/Output Error: " + exception);
                } catch (LineUnavailableException exception) {
                    throw new RuntimeException("Sound: Line Unavailable: " + exception);
                }
                clip.setFramePosition(0);  // Must always rewind!
                clip.start();
                clip.loop(Clip.LOOP_CONTINUOUSLY);
                if (dog.getHunger_lvl() > 0) {
                    clip.stop();
                }
            }

            if (dog.getHappiness_lvl() == 0)
            {
                dog.setMood("Sad");
                fileName = ("Walk.wav");
                try {
                    File file = new File(fileName);
                    if (file.exists()) {
                        clip = AudioSystem.getClip();
                        AudioInputStream sound = AudioSystem.getAudioInputStream(file.toURI().toURL());
                        clip.open(sound);
                    } else {
                        throw new RuntimeException("Sound: file not found: " + fileName);
                    }
                } catch (MalformedURLException exception) {
                    throw new RuntimeException("Sound: Malformed URL: " + exception);
                } catch (UnsupportedAudioFileException exception) {
                    throw new RuntimeException("Sound: Unsupported Audio File: " + exception);
                } catch (IOException exception) {
                    throw new RuntimeException("Sound: Input/Output Error: " + exception);
                } catch (LineUnavailableException exception) {
                    throw new RuntimeException("Sound: Line Unavailable: " + exception);
                }
                clip.setFramePosition(0);  // Must always rewind!
                clip.start();
                clip.loop(Clip.LOOP_CONTINUOUSLY);
                if (dog.getHappiness_lvl() > 0) {
                    clip.stop();
                }
            }

            if (dog.getEnergy_lvl() == 0)
            {
                dog.setMood("Tired");
                fileName = "Sleep.wav";
                try {
                    File file = new File(fileName);
                    if (file.exists()) {
                        clip = AudioSystem.getClip();
                        AudioInputStream sound = AudioSystem.getAudioInputStream(file.toURI().toURL());
                        clip.open(sound);
                    } else {
                        throw new RuntimeException("Sound: file not found: " + fileName);
                    }
                } catch (MalformedURLException exception) {
                    throw new RuntimeException("Sound: Malformed URL: " + exception);
                } catch (UnsupportedAudioFileException exception) {
                    throw new RuntimeException("Sound: Unsupported Audio File: " + exception);
                } catch (IOException exception) {
                    throw new RuntimeException("Sound: Input/Output Error: " + exception);
                } catch (LineUnavailableException exception) {
                    throw new RuntimeException("Sound: Line Unavailable: " + exception);
                }
                clip.setFramePosition(0);  // Must always rewind!
                clip.start();
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            }

            if (dog.getEnergy_lvl()>0 || dog.getHappiness_lvl()>0 || dog.getHunger_lvl()>0) {
                if (clip.isRunning()) {
                    clip.stop();
                }
            }

            hungerLabel.setText("                 Hunger lvl: " + dog.getHunger_lvl());
            happinessLabel.setText("Happiness lvl: " + dog.getHappiness_lvl());
            energyLabel.setText("Energy lvl: " + dog.getEnergy_lvl());
            status.setText("Status: "+dog.getMood());
        }
    }
}