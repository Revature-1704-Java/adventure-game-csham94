package com.game;

import java.util.Scanner;
import java.util.HashMap;

public class Game {
    private Room currentRoom;
    private Scanner inputReader;
    private HashMap<String, Room> generatedRooms;

    public static void main(String[] args) {
        Game game = new Game();
        game.play();
    }

    public Game() {
        generatedRooms = new HashMap<>();
        createRooms();
    }

    private void createRooms() {
        Room closet, centerRoom, hallway, bathroom, purpleDoor, outside;
      
        closet = new Room("Guess what buddy, you're boned! You got trapped in a closet! In front of you is the CLOSET DOOR, and to your left is a pair of SHOES. You gotta get out somehow!");
        centerRoom = new Room("You open up the closet door, as it turns out, you never locked it in the first place. Nice. You are in a room you do not recognize, which is kinda weird, considering you had to come through here to get in the closet in the first place. There's a pile of BOXES to your right (which might contain something neat), a WINDOW to your left, and there is a HALLWAY in front of you.");
        hallway = new Room("You've stepped into the hallway, which is quite long. There's a lot of locked doors, covered in graffiti, with one that even says 'DON'T DEAD OPEN INSIDE'. However, there is one door open, which leads to an empty BATHROOM, as well as a GLASS DOOR all the way at the end of the hallway. There's also a peculiar PURPLE DOOR, which feels strangly enticing.");
        bathroom = new Room("Oh man, this bathroom is...stale. Like it hasn't been tended to in weeks, but nobody has used it since it was last cleaned. Once you walked in here, you realized that the toilet was for decoration only, and was completely empty, not even hooked up to any sort of plumbing. The sink has what appears to be a writhing BAG in it. The DOOR out is behind you.");
        purpleDoor = new DeathRoom();
        purpleDoor.setDescription("You died of a myocardial infarction as soon as you touched the door. Whoops, sorry. Shit happens, I guess.");
        outside = new Room("Oh man, sweet relief! You've made it outside! You've escaped from the closet! Well, uh...I guess you escaped from the closet a while ago, but whatever, you've made it outside! And, uh...well...uh...it's kinda warm out here. You kinda miss the air conditioning inside. Too bad the doors locked behind you, sucker! Bad end!");
        
        closet.setInteractives("shoes", "You play around with the shoes for a lil bit. It makes you feel a little bit better, though it doesn't really accomplish anything.");
        closet.setInteractives("closet door", "Hey, turns out the door is unlocked! Aha, can't believe you didn't even notice that. You walk through the door, and, as the door closes, you can hear a lock click shut behind you.");

        centerRoom.setInteractives("boxes", "Going through the boxes, you realize most of these are empty. However, some boxes have contents. One box has some gravel within it; another seems to be filled to the brim with clean, white fingerbones; and one has a sweet stand mixer in it. You don't feel like transporting any of this stuff, though, so you leave it all be.");
        centerRoom.setInteractives("window", "It's kinda dreary outside. Very overcast, but the air seems...dead. Completely still, completely quiet. You see what appear to be people slowly shuffling around, though they seem very dark. Like silhouettes. You could even swear they were transluscent, but that'd be weird, so your eyes are probably just messing up.");
        centerRoom.setInteractives("hallway", "You enter the hallway, and as you pass through the hallway entrance, the lights in the room behind you dim slowly into pitch blackness. There seems to be a hard cut-off between the light in the hallway and the darkness of the room. That doesn't seem possible, but whatever, you don't know a damn thing about light. You're not a physicist. You'll just avoid going back there for now.");

        hallway.setInteractives("don't dead open inside", "Weird graffiti. How do you dead open something?");
        hallway.setInteractives("bathroom", "You enter the bathroom, and, for the first time, the path behind doesn't get locked! Wild. The door remains open, though the light in the bathroom flickers once you step in.");
        hallway.setInteractives("glass door", "The glass door opens pretty easily. Man, you weren't really trapped at any point during this whole adventure, were you?");
        hallway.setInteractives("purple door", "The door glows a bright purple door, and it feels like it's compelling you to touch it. You gotta touch it. Dude, let's touch it. You touch the door.");

        bathroom.setInteractives("bag", "Yeah, the fact that the bag's moving is kinda funky. Well, whatever, you grasp the opening of the bag and peer in, and...oh. Okay. Ah. Alrighty. \n \n You put the bag back in the sink as you file that lil interaction into the repression cabinet.");
        bathroom.setInteractives("door", "What an odd bathroom. Whatever, lucky for you, you don't have to use the bathroom. You leave, leaving the door open once again.");

        generatedRooms.put("centerRoom", centerRoom);
        generatedRooms.put("hallway", hallway);
        generatedRooms.put("bathroom", bathroom);
        generatedRooms.put("purpleDoor", purpleDoor);
        generatedRooms.put("outside", outside);

        currentRoom = closet;
    }

    public void play() {            
        inputReader = new Scanner(System.in);
        boolean finished = false;
        System.out.println("Welcome to ClosetQuest! Simply type in the item you want to interact with, all interactable items are in UPPERCASE.");
        System.out.println("");
        System.out.println("GET PSYCHED");
        System.out.println("");
        System.out.println(currentRoom.getDescription());
        System.out.println("");

        String commandInput = "";        
        do {
            commandInput = inputReader.nextLine();
            commandInput = commandInput.toLowerCase();

            if (!currentRoom.checkKey(commandInput)) {
                System.out.println("Try again.");
                System.out.println("");
            } else {
                System.out.println(currentRoom.getInteractives(commandInput));
                switch (commandInput) {
                    case "closet door": 
                        currentRoom = generatedRooms.get("centerRoom"); 
                        System.out.println("");                    
                        System.out.println(currentRoom.getDescription()); 
                        break;
                    case "door":
                    case "hallway": 
                        currentRoom = generatedRooms.get("hallway");
                        System.out.println("");
                        System.out.println(currentRoom.getDescription()); 
                        break;
                    case "bathroom": 
                        currentRoom = generatedRooms.get("bathroom");
                        System.out.println("");
                        System.out.println(currentRoom.getDescription()); 
                        break;
                    case "purple door":
                        currentRoom = generatedRooms.get("purpleDoor");
                        System.out.println("");                                            
                        System.out.println(currentRoom.getDescription()); 
                        break;
                    case "glass door": 
                        currentRoom = generatedRooms.get("outside");
                        System.out.println("");                                            
                        System.out.println(currentRoom.getDescription()); 
                        finished = true; 
                        break;
                    default: break;
                }
                if (currentRoom.canKillYou() == true) {
                    finished = true;
                }
                System.out.println("");
            }
         } while (finished == false);
    }
}