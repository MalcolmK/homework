package edu.mccc.ist239.chat;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

import javax.swing.*;

import com.cbthinkx.util.Debug;

/**
 * The actual client GUI. A ChatClient object is used to send/received messages from the server.
 */
public class ChatTest extends JPanel implements ChatClientListener, ChatLoginListener, ChatFileListener {
    //private JFrame jFrame;
    private ChatClient chatClient;
    private BuddyList buddyList;
    private JTextArea nameField;
    private JTextArea chatText;
    private JTextField inputField;

    public ChatTest(String hostIp) {
        this.chatClient = new ChatClient(
            hostIp
        );
        chatClient.addChatClientListener(this);
        chatClient.addChatLoginListener(this);
        initGUI();
    }
    public ChatTest() {
        this("127.0.0.1");
    }
    private void initGUI() {
        //GUI
        setLayout(new BorderLayout());
        chatText = new JTextArea();
        chatText.setEditable(false);
        chatText.setBorder(
            BorderFactory.createTitledBorder(
                "Chat"
            )
        );
        JScrollPane scrollPane = new JScrollPane(chatText);

        JButton sendButton = new JButton("Send");
        sendButton.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    sendMessage();
                }
            }
        );
        JPanel namePanel = new JPanel();
        namePanel.setLayout(
            new BorderLayout()
        );
        namePanel.setBorder(
            BorderFactory.createLineBorder(
                Color.BLACK
            )
        );
        namePanel.add(
            new JLabel(
                "Name: "
            ),
            BorderLayout.WEST
        );
        nameField = new JTextArea();
        nameField.setRows(1);
        namePanel.add(
            nameField,
            BorderLayout.CENTER
        );

        inputField = new JTextField();
        inputField.addKeyListener(
            new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        sendMessage();
                    }
                }
            }
        );
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());
        inputPanel.add(
            sendButton,
            BorderLayout.EAST
        );
        inputPanel.add(
            inputField,
            BorderLayout.CENTER
        );

        add(
            scrollPane,
            BorderLayout.CENTER
        );
        add(
            inputPanel,
            BorderLayout.SOUTH
        );

        buddyList = new BuddyList(this);
        add(
            buddyList,
            BorderLayout.EAST
        );

        setPreferredSize(
            new Dimension(
                640,
                480
            )
        );
    }

    /**
     * Attaches the menu to the given JFrame
     */
    private void makeMenu(JFrame frame) {
        JMenuBar menuBar;
        JMenu menu, submenu;
        JMenuItem menuItem;
        JRadioButtonMenuItem rbMenuItem;
        JCheckBoxMenuItem cbMenuItem;
        //Create the menu bar.
        menuBar = new JMenuBar();
        
        //Build the first menu.
        menu = new JMenu("A Menu");
        
        menu.setMnemonic(KeyEvent.VK_A);
        menu.getAccessibleContext().setAccessibleDescription(
                        "The only menu in this program that has menu items");
        //menuBar.add(menu);

        //a group of JMenuItems
        menuItem = new JMenuItem("A text-only menu item",
                KeyEvent.VK_T);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                    KeyEvent.VK_1, ActionEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "This doesn't really do anything");
        menu.add(menuItem);

        menuItem = new JMenuItem("Both text and icon",
                new ImageIcon("images/middle.gif"));
        menuItem.setMnemonic(KeyEvent.VK_B);
        menu.add(menuItem);

        menuItem = new JMenuItem(new ImageIcon("images/middle.gif"));
        menuItem.setMnemonic(KeyEvent.VK_D);
        menu.add(menuItem);

        //a group of radio button menu items
        menu.addSeparator();
        ButtonGroup group = new ButtonGroup();
        rbMenuItem = new JRadioButtonMenuItem("A radio button menu item");
        rbMenuItem.setSelected(true);
        rbMenuItem.setMnemonic(KeyEvent.VK_R);
        group.add(rbMenuItem);
        menu.add(rbMenuItem);

        rbMenuItem = new JRadioButtonMenuItem("Another one");
        rbMenuItem.setMnemonic(KeyEvent.VK_O);
        group.add(rbMenuItem);
        menu.add(rbMenuItem);

        //a group of check box menu items
        menu.addSeparator();
        cbMenuItem = new JCheckBoxMenuItem("A check box menu item");
        cbMenuItem.setMnemonic(KeyEvent.VK_C);
        menu.add(cbMenuItem);

        cbMenuItem = new JCheckBoxMenuItem("Another one");
        cbMenuItem.setMnemonic(KeyEvent.VK_H);
        menu.add(cbMenuItem);

        //a submenu
        menu.addSeparator();
        submenu = new JMenu("A submenu");
        submenu.setMnemonic(KeyEvent.VK_S);

        menuItem = new JMenuItem("An item in the submenu");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                    KeyEvent.VK_2, ActionEvent.ALT_MASK));
        submenu.add(menuItem);

        menuItem = new JMenuItem("Another item");
        submenu.add(menuItem);
        menu.add(submenu);

        //Build second menu in the menu bar.
        menu = new JMenu("Actions");
        menu.setMnemonic(KeyEvent.VK_N);
        menu.getAccessibleContext().setAccessibleDescription(
                "Chat actions found here");

        menuItem = new JMenuItem("Sign On",
                KeyEvent.VK_O);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                    KeyEvent.VK_1, ActionEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "Sign on to DKChat");
        menuItem.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.out.println(
                        "Sign on pressed"
                    );
                    new LoginWindow(ChatTest.this, chatClient);
                }
            }
        );
        menu.add(menuItem);

        menuItem = new JMenuItem("Sign Off",
                KeyEvent.VK_F);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                    KeyEvent.VK_3, ActionEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "Sign off of DKChat");
        menuItem.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.out.println(
                        "Sign off pressed"
                    );
                    //Clear out the buddy list
                    buddyList.clearBuddies();
                    chatClient.logOut();
                }
            }
        );
        menu.add(menuItem);

        menuBar.add(menu);

        frame.setJMenuBar(menuBar);
    }

    public static void main(String[] sa) {
		final JFrame jFrame = new JFrame();
		jFrame.setTitle("Chat Demo");
		//jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final ChatTest ct;
        if (sa.length == 1) {
            ct = new ChatTest(
                sa[0]
            );
        } else {
            ct = new ChatTest();
        }
        jFrame.add(
            ct
        );
        ct.makeMenu(jFrame);

        jFrame.addWindowListener(
            new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    System.out.println("Window Closing");
                    //Disconnect from the server
                    ct.getChatClient().logOut();
                    jFrame.dispose();
                    System.exit(0);
                }
            }
        );
		jFrame.pack();
		jFrame.setLocationRelativeTo(null);
		jFrame.setVisible(true);
    }

    public ChatClient getChatClient() {
        return chatClient;
    }

    public void messageReceived(ChatClientEvent e) {
        System.out.println("Message received!: " + e.getMessage());
        String msg = e.getMessage();
        if (msg.startsWith("chat")) {
            //Chop off the prefix
            msg = msg.substring(
                5,
                msg.length() 
            );
            chatText.append(msg + "\n");
        } else if (msg.startsWith("buddy")) {
            //Add buddy to list
            msg = msg.substring(
                6,
                msg.length() 
            );
            buddyList.addBuddy(msg, false);
        }
    }

    public void loginEvent(boolean success) {
        JFrame rootFrame = getRootFrame(this);
        if (success) {
            rootFrame.setTitle("Chat Demo: Online");
        } else {
            rootFrame.setTitle("Chat Demo: Offline");
        }
    }

    private JFrame getRootFrame(Container container) {
        Container parent = container.getParent();
        if (parent == null) {
            //if (container.class == JFrame.class) {
            if (container instanceof JFrame) {
                return (JFrame) container;
            } else {
                return null;
            }
        } else {
            return getRootFrame(parent);
        }
    }

    /**
     * Send a standard chat message
     */
    private void sendMessage() {
        //chatClient.sendMessage(
        chatClient.sendChatMessage(
            //nameField.getText() + ": " + inputField.getText()
            inputField.getText()
        );
        inputField.setText("");
    }

    /**
     * Start a new private IM conversation
     */
    public void privateIM(String username) {
        new ChatWindow(ChatTest.this, username, chatClient);
    }
     
    public void addBuddy(String username) {
        Debug.println("ChatTest.addBuddy: " + username);
        buddyList.addBuddy(username, true);
    }
    public void removeBuddy(String username) {
        Debug.println("ChatTest.removeBuddy: " + username);
        buddyList.removeBuddy(username, true);
    }

	/**
	 * Draw a confirmation window to accept file transfer and provide filename
	 */
    public void fileEvent(Socket hostSocket, String remoteFileName) {
        Debug.println("ChatTest.fileEvent: " + hostSocket + ": " + remoteFileName);
		//Launch file dialog
		new FileAcceptWindow(
			chatClient,
			hostSocket,
			remoteFileName
		);
	}
}
