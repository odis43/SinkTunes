import java.util.Calendar;
import java.util.Scanner;

public class SinkCraft {
	public static int main_menu() {
		Scanner input = new Scanner(System.in);
		System.out.println("MAIN MENU (Select one of the options of above):");
		System.out.println("(1) Run SinkTunes");
		System.out.println("(2) About Us (What is SinkTunes?)");
		System.out.println("(3) Close Program");
		System.out.print("\nIndex:");

		String sOption = input.nextLine();
		int option = Integer.parseInt(sOption);
		return (option);
	}

	public static void about_us() {
		System.out.println("Meet the team!:");
		System.out.println("Project Manager: Oditha Amarasinghe (Waterloo Computer Science / Laurier BBA");
		System.out.println("Software Engineer Tech Lead: Taha Zaryab (Waterloo Computer Science COOP");
		System.out.println("Junior Software Developer: Kevin Le (University of Toronto Computer Science COOP");
		System.out.println("------------------------------------------------------------------------------");
		System.out.println(
				"SinkTunes is the latest innovation in music.  Ampflify your next milestone through sy(ink)nking a playlist to match any important occasion in your life");
		System.out.println("Here's how it works: ");
		System.out.println(
				"1. ENTER YOUR INFORMATION: The program will ask you for some basic information such as: 'What is your special occasion?', 'What time is your event (DDMMYYYY, H:M:S:MS')");
		System.out.println("2. Import your songs: WE'D ASK YOU THE FOLLOWING QUESTIONS:");
		System.out.println(
				"How many songs are in your playlist, Enter a specific song within the list (Artist name;Song name;MM;SS): ");
	}


//	

	public static void main(String[] args) {

		boolean keep_running = true;
		Scanner input = new Scanner(System.in);
		String occasion = "";
		String month = "";
		String sDay = "", sHour = "", sMinute = "";
		String sPlaylist = "", sSong = "",targetsong = "";
		int day = 0, hours = 0, minute = 0, playlist = 0, songnumbertarget=0,year = 2022;
		boolean newyears = false;
		System.out.println("Welcome to SinkTunes!");
		do {
			int menu_option = main_menu();
			do {
				if (menu_option < 1 || menu_option > 3) {
					System.out.println("Invalid input! Please try again.");
					menu_option = main_menu();
				}
			} while (menu_option < 1 || menu_option > 3);

			if (menu_option == 1) {
				System.out.print("\nWhat is your special occasion (ex: Friends's Birthday, New Years etc.): ");
				occasion = input.nextLine();
				System.out.print("\nWhat is the month of your special occasion?(ex: June, July, August):");
				month = input.nextLine();
				System.out.print("\nWhat is the day of the month of your special occasion?:");
				sDay = input.nextLine();
				day = Integer.parseInt(sDay);
				System.out.print("\n What is the hour on the day of the occasion (using 24 hour clock)?:");
				sMinute = input.nextLine();
				minute = Integer.parseInt(sMinute);
				if (minute<=0 && day!=1) {
					 minute = 24;
					 day = day-1;
					 if (month.equals("January")&& day==1) {
						newyears=true; 
					 }
				 }
				 
				System.out.print("\nHow many songs are in your desired playlist?:");
				sPlaylist = input.nextLine();
				playlist = Integer.parseInt(sPlaylist);
				String[][] syncplaylist = new String[playlist][2];
				int[][] durationsongs = new int[playlist][2];
				for (int i = 0; i < playlist; i++) {
					String temp = "";
					System.out.print("\nEnter song name of song #" + (i + 1) + ":");
					syncplaylist[i][0] = input.nextLine();
					System.out.print("\nEnter the name of the artist of " + syncplaylist[i][0] + ":");
					syncplaylist[i][1] = input.nextLine();
					System.out.print("\nEnter the total minutes in " + syncplaylist[i][0] + " (not including seconds):");
					temp = input.nextLine();
					durationsongs[i][0] = Integer.parseInt(temp);
					System.out.print("\nEnter the seconds of " + syncplaylist[i][0] + " after the total minutes:");
					temp = input.nextLine();
					durationsongs[i][1] = Integer.parseInt(temp);

				}
				System.out.print("\nWhat is the name of target song you wanted to sync with the "+occasion+"?:");
				targetsong=input.nextLine();
					for (int i = 0;i<playlist;i++) {
						if (syncplaylist[i][0].equals(targetsong)) {
							songnumbertarget=i;
						}
					}
					int[][] durationtargetsong = new int[1][2];
					String temp="";
					System.out.print("\nEnter the minutes to where you want to sync in " + syncplaylist[songnumbertarget][0] + " (not including seconds):");
					temp = input.nextLine();
					durationtargetsong[0][0] = Integer.parseInt(temp);
					System.out.print("\nEnter the seconds to where you want to sync in " + syncplaylist[songnumbertarget][0] + " after the total minutes:");
					temp = input.nextLine();
					durationtargetsong[0][1] = Integer.parseInt(temp);
					System.out.println("Generating playlist...");
					
					
					int summinutes = 0, sumseconds= 0;
					for (int i = 0;i<playlist;i++){
						if (i!=songnumbertarget) {
						summinutes+=durationsongs[i][0];
						sumseconds+=durationsongs[i][1];
						}
						
					}
					summinutes+=durationtargetsong[songnumbertarget][0];
					sumseconds+=durationtargetsong[songnumbertarget][1];
					sumseconds+=((summinutes)*60);
					
					int hourtoplay = minute-1,secondtoplay=0, minutetoplay=0;
					
					minutetoplay=sumseconds/60;
					secondtoplay = sumseconds%60;
					minutetoplay=60-minutetoplay;
					secondtoplay=60-secondtoplay;
					
					System.out.println("PLAYLIST SUCCESSFULLY GENERATED....");
					System.out.println(occasion+" playlist");
					int counter=0;
					for (int i=0; i<playlist;i++) {
						
	
						if (i!=songnumbertarget) {
							counter++;
						System.out.println("Song #"+counter+": "+syncplaylist[i][0]+" by "+syncplaylist[i][1]);
						}
					}
					System.out.println("Song #"+(playlist)+": "+syncplaylist[songnumbertarget][0]+" by "+syncplaylist[songnumbertarget][1]);
					System.out.println("We recommend starting the playlist at hour "+hourtoplay+", at minute "+minutetoplay+", and at second "+secondtoplay);
					keep_running=false;
					
					
			}

			else if (menu_option == 2) {
				about_us();

			}

		} while (keep_running == true);
	}

}
