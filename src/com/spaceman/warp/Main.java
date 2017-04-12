package src.com.spaceman.warp;

import java.util.ArrayList;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	public void onEnable() {
		Logger logger = Logger.getLogger("Minecraft");
		logger.info("Warp has been enabled");

		// checks if exist
		if (!(getConfig().contains("settings"))) {
			getConfig().set("settings.README",
					"THIS IS VERY IMPORTANT STUFF, DO NOT DELETE ANYTHING OF THIS. YOU ARE ABLE TO EDIT IT. IF YOU ARE GETTING ERRORS DELETE THE WHOLE SETTINGS TREE. IT WILL AUTO REPAIR ITSELF WHEN RELAOD OR RESTART");
			getConfig().set("settings.WrongUse", "§cwrong use, use §3/warp help §cfor more info");
			getConfig().set("settings.Exist", "§cthe warp §a/warp/ §calready exist");
			getConfig().set("settings.DontExist",
					"§cthe warp §a/warp/ §cdoesn't exist, create a new one using §a/warp add [warp name]");
			getConfig().set("settings.AddWarp", "§2succesfully added the warp §a/warp/");
			getConfig().set("settings.RemoveWarp", "§2succesfully removed the warp §a/warp/");
			getConfig().set("settings.Edited", "§2succesfully edited the warp §a/warp/");
			getConfig().set("settings.EditedExtra", "§2succesfully edited §a/extra/ §2of the warp §a/warp/");
			getConfig().set("settings.NotOnline", "§cthis player isn't online");
			getConfig().set("settings.YouAreOwner", "§cyou can't make yourselve the owner, you already are");
			getConfig().set("settings.NewOwner", "§2the owner of §a/warp/ §2is now succesfully set to §a/newowner/");
			getConfig().set("settings.TrueFalse", "§cyou ony can use §etrue §cor §efalse");
			getConfig().set("settings.ToPrivate", "§2the warp §a/warp/ §2is succesfully set to §9private");
			getConfig().set("settings.ToOpen", "§2the warp §a/warp/ §2is succesfully set to §9open");
			getConfig().set("settings.ListWhitelistEmpty", "§2this list is empty");
			getConfig().set("settings.ListWhitelistMessage", "§2whitelist: §a/warp/ §2(§a/size/§2)");
			getConfig().set("settings.ListWhitelist", "§a/list/");
			getConfig().set("settings.MyListEmpty", "§2this list is empty");
			getConfig().set("settings.MyListMessage", "§2your warp list: §2(§a/size/§2)");
			getConfig().set("settings.MyListList", "§a/list/");
			getConfig().set("settings.AddWhitelist",
					"§2the player §a/newplayer/ §2is succesfully added to your whitelist of the warp §a/warp/");
			getConfig().set("settings.RemoveWhitelist",
					"§2the player §a/oldplayer/ §2is succesfully removed to your whitelist of the warp §a/warp/");
			getConfig().set("settings.ListWhitelistAlreadyExist",
					"§cthe player §a/addplayer/ §cis already in the whitelist of the warp §a/warp/");
			getConfig().set("settings.ListWhitelistDontExist",
					"§cthe player §a/removeplayer/ §cisn't in the whitelist of the warp §a/warp/");
			getConfig().set("settings.NotOwner",
					"§cyou arn't the owner of this warp, contact §a/owner/ §cfor futher information");
			getConfig().set("settings.Warped", "§2succesfully warped to §a/warp/");
			getConfig().set("settings.ItsPrivate",
					"§cthis warp is private, ask §a/owner/ §cif he put you in his whitelist");
			getConfig().set("settings.NoWorld",
					"§cthis world doesn't exist anymore, use §3/warp edit §a/warp/ §cto fix the world");
			getConfig().set("settings.ListWarpMessage", "§2warp list:");
			getConfig().set("settings.ListWarp", "§a/list/");
			getConfig().set("settings.Info", "§2info of the warp §a/warp/§2:");
			getConfig().set("settings.X", "§2x: §a/x/");
			getConfig().set("settings.Y", "§2y: §a/y/");
			getConfig().set("settings.Z", "§2z: §a/z/");
			getConfig().set("settings.Yaw", "§2yaw: §a/yaw/");
			getConfig().set("settings.Pitch", "§2pitch: §a/pitch/");
			getConfig().set("settings.World", "§2world: §a/world/");
			getConfig().set("settings.NotOP", "§cyou arn't an op of this server");

			saveConfig();
		}
		if (!(getConfig().contains("mylist"))) {
			getConfig().set("mylist", "");
			saveConfig();
		}
		if (!(getConfig().contains("warps"))) {
			getConfig().set("warps", "");
			saveConfig();
		}
	}

	public void onDisable() {
		Logger logger = Logger.getLogger("Minecraft");
		logger.info("Warp has been disabled");
	}

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {

		Player player = (Player) sender;

		if (sender instanceof Player) {

			if (commandLabel.equalsIgnoreCase("warp")) {

				if (args.length == 0) {
					player.sendMessage(
							getConfig().getString("settings.WrongUse").replaceAll("/player/", player.getName()));
					////////////////////////////////////////////////////////////////////////////////////////////////////////////////// list
				} else if (args[0].equalsIgnoreCase("list")) {

					if (args.length == 1) {

						if (getConfig().getString("warps").equalsIgnoreCase("")
								|| getConfig().getString("warps").equalsIgnoreCase("{}")) {
							player.sendMessage(getConfig().getString("settings.ListWarpMessage").replaceAll("/player/",
									player.getName()));
							return false;
						}

						player.sendMessage(getConfig().getString("settings.ListWarpMessage").replaceAll("/player/",
								player.getName()));
						for (String list : getConfig().getConfigurationSection("warps").getKeys(false)) {
							player.sendMessage(getConfig().getString("settings.ListWarp")
									.replaceAll("/player/", player.getName()).replaceAll("/list/", list));
						}

					} else {
						player.sendMessage(
								getConfig().getString("settings.WrongUse").replaceAll("/player/", player.getName()));
					}
					////////////////////////////////////////////////////////////////////////////////////////////////////////////////// mylist
				} else if (args[0].equalsIgnoreCase("mylist")) {

					if (args.length == 1) {

						ArrayList<String> mylist = (ArrayList<String>) getConfig()
								.getStringList("mylist." + player.getName());

						StringBuilder str = new StringBuilder();
						str.append(mylist.size());
						String mylistsize = str.toString();

						if (mylistsize.equals("0")) {
							player.sendMessage(getConfig().getString("settings.MyListEmpty").replaceAll("/player/",
									player.getName()));
							return false;
						}

						player.sendMessage(getConfig().getString("settings.MyListMessage")
								.replaceAll("/player/", player.getName()).replaceAll("/size/", mylistsize));

						for (String s : mylist) {
							player.sendMessage(getConfig().getString("settings.MyListList")
									.replaceAll("/player/", player.getName()).replaceAll("/list/", s));
						}

						return false;
					} else {
						player.sendMessage(
								getConfig().getString("settings.WrongUse").replaceAll("/player/", player.getName()));
					}
					////////////////////////////////////////////////////////////////////////////////////////////////////////////////// info
				} else if (args[0].equalsIgnoreCase("info")) {
					if (args.length == 2) {
						// checks if exist
						if (!(getConfig().contains("warps." + args[1]))) {
							player.sendMessage(getConfig().getString("settings.DontExist")
									.replaceAll("/player/", player.getName()).replaceAll("/warp/", args[1]));
							return false;
						}

						// checks if owner
						if (!(player.getName()
								.equalsIgnoreCase(getConfig().getString("warps." + args[1] + ".owner")))) {
							player.sendMessage(getConfig().getString("settings.NotOwner")
									.replaceAll("/player/", player.getName()).replaceAll("/warp/", args[1])
									.replaceAll("/owner/", getConfig().getString("warps." + args[1] + ".owner")));
							return false;
						}

						player.sendMessage(getConfig().getString("settings.Info")
								.replaceAll("/player/", player.getName()).replaceAll("/warp/", args[1]));

						String x = getConfig().getString("warps." + args[1] + ".X"),
								y = getConfig().getString("warps." + args[1] + ".Y"),
								z = getConfig().getString("warps." + args[1] + ".Z"),
								pitch = getConfig().getString("warps." + args[1] + ".pitch"),
								yaw = getConfig().getString("warps." + args[1] + ".yaw");
						String world = getConfig().getString("warps." + args[1] + ".world");

						player.sendMessage(getConfig().getString("settings.X").replaceAll("/player/", player.getName())
								.replaceAll("/x/", x).replaceAll("/warp/", args[1]));
						player.sendMessage(getConfig().getString("settings.Y").replaceAll("/player/", player.getName())
								.replaceAll("/y/", y).replaceAll("/warp/", args[1]));
						player.sendMessage(getConfig().getString("settings.Z").replaceAll("/player/", player.getName())
								.replaceAll("/z/", z).replaceAll("/warp/", args[1]));
						player.sendMessage(
								getConfig().getString("settings.Yaw").replaceAll("/player/", player.getName())
										.replaceAll("/yaw/", yaw).replaceAll("/warp/", args[1]));
						player.sendMessage(
								getConfig().getString("settings.Pitch").replaceAll("/player/", player.getName())
										.replaceAll("/pitch/", pitch).replaceAll("/warp/", args[1]));
						player.sendMessage(
								getConfig().getString("settings.World").replaceAll("/player/", player.getName())
										.replaceAll("/world/", world).replaceAll("/warp/", args[1]));
					} else {
						player.sendMessage(
								getConfig().getString("settings.WrongUse").replaceAll("/player/", player.getName()));
					}
					///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// add//////////////
				} else if (args[0].equalsIgnoreCase("add")) {
					if (args.length == 2) {
						if (args[1].equalsIgnoreCase("add") || args[1].equalsIgnoreCase("whitelist")
								|| args[1].equalsIgnoreCase("edit") || args[1].equalsIgnoreCase("remove")
								|| args[1].equalsIgnoreCase("settings") || args[1].equalsIgnoreCase("help")
								|| args[1].equalsIgnoreCase("settings") || args[1].equalsIgnoreCase("list")
								|| args[1].equalsIgnoreCase("mylist")) {
							player.sendMessage("§cthis is an build in command at §3/warp");
							player.sendMessage("§cyou can't do anything whith this");
							return false;
						}

						// warp add [warp name]

						if (!(getConfig().contains("warps." + args[1]))) {

							// set all data in config
							getConfig().set("warps." + args[1] + ".X", player.getLocation().getX());
							getConfig().set("warps." + args[1] + ".Y", player.getLocation().getY());
							getConfig().set("warps." + args[1] + ".Z", player.getLocation().getZ());
							getConfig().set("warps." + args[1] + ".pitch", player.getLocation().getPitch());
							getConfig().set("warps." + args[1] + ".yaw", player.getLocation().getYaw());
							getConfig().set("warps." + args[1] + ".owner", player.getName());
							getConfig().set("warps." + args[1] + ".private", false);
							getConfig().set("warps." + args[1] + ".world", player.getLocation().getWorld().getName());
							ArrayList<String> whitelist = new ArrayList<String>();
							getConfig().set("warps." + args[1] + ".whitelist", whitelist);
							player.sendMessage(getConfig().getString("settings.AddWarp")
									.replaceAll("/player/", player.getName()).replaceAll("/warp/", args[1]));

							ArrayList<String> mylist = (ArrayList<String>) getConfig()
									.getStringList("mylist." + player.getName());
							mylist.add(args[1]);
							getConfig().set("mylist." + player.getName(), mylist);

							// save config
							saveConfig();

						} else {
							player.sendMessage(getConfig().getString("settings.Exist")
									.replaceAll("/player/", player.getName()).replaceAll("/warp/", args[1]));
						}
					} else if (args.length == 3) {
						// warp add [warp name] false
						if (args[2].equalsIgnoreCase("false")) {
							if (!(getConfig().contains("warps." + args[1]))) {
								// set all data in config
								getConfig().set("warps." + args[1] + ".X", player.getLocation().getX());
								getConfig().set("warps." + args[1] + ".Y", player.getLocation().getY());
								getConfig().set("warps." + args[1] + ".Z", player.getLocation().getZ());
								getConfig().set("warps." + args[1] + ".pitch", player.getLocation().getPitch());
								getConfig().set("warps." + args[1] + ".yaw", player.getLocation().getYaw());
								getConfig().set("warps." + args[1] + ".owner", player.getName());
								getConfig().set("warps." + args[1] + ".private", false);
								getConfig().set("warps." + args[1] + ".world",
										player.getLocation().getWorld().getName());
								// getConfig().set(args[1] + ".world",
								// player.getLocation().getWorld());
								ArrayList<String> whitelist = new ArrayList<String>();
								getConfig().set("warps." + args[1] + ".whitelist", whitelist);
								player.sendMessage(getConfig().getString("settings.AddWarp")
										.replaceAll("/player/", player.getName()).replaceAll("/warp/", args[1]));

								ArrayList<String> mylist = (ArrayList<String>) getConfig()
										.getStringList("mylist." + player.getName());
								mylist.add(args[1]);
								getConfig().set("mylist." + player.getName(), mylist);

								// save config
								saveConfig();

							} else {
								player.sendMessage(getConfig().getString("settings.Exist")
										.replaceAll("/player/", player.getName()).replaceAll("/warp/", args[1]));
							}

						} else if (args[2].equalsIgnoreCase("true")) {
							// warp add [warp name] false
							if (!(getConfig().contains("warps." + args[1]))) {
								// set all data in config
								getConfig().set("warps." + args[1] + ".X", player.getLocation().getX());
								getConfig().set("warps." + args[1] + ".Y", player.getLocation().getY());
								getConfig().set("warps." + args[1] + ".Z", player.getLocation().getZ());
								getConfig().set("warps." + args[1] + ".pitch", player.getLocation().getPitch());
								getConfig().set("warps." + args[1] + ".yaw", player.getLocation().getYaw());
								getConfig().set("warps." + args[1] + ".owner", player.getName());
								getConfig().set("warps." + args[1] + ".private", true);
								getConfig().set("warps." + args[1] + ".world",
										player.getLocation().getWorld().getName());
								// getConfig().set(args[1] + ".world",
								// player.getLocation().getWorld());
								ArrayList<String> whitelist = new ArrayList<String>();
								getConfig().set("warps." + args[1] + ".whitelist", whitelist);
								player.sendMessage(getConfig().getString("settings.AddWarp")
										.replaceAll("/player/", player.getName()).replaceAll("/warp/", args[1]));

								ArrayList<String> mylist = (ArrayList<String>) getConfig()
										.getStringList("mylist." + player.getName());
								mylist.add(args[1]);
								getConfig().set("mylist." + player.getName(), mylist);

								// save config
								saveConfig();

							} else {
								player.sendMessage(getConfig().getString("settings.Exist")
										.replaceAll("/player/", player.getName()).replaceAll("/warp/", args[1]));
							}
						} else {
							player.sendMessage(getConfig().getString("settings.TrueFalse")
									.replaceAll("/player/", player.getName()).replaceAll("/warp/", args[1]));
						}
					} else {
						player.sendMessage(
								getConfig().getString("settings.WrongUse").replaceAll("/player/", player.getName()));
					}
					//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// remove///////////////////////
				} else if (args[0].equalsIgnoreCase("remove")) {
					if (args.length == 2) {
						if (args[1].equalsIgnoreCase("add") || args[1].equalsIgnoreCase("whitelist")
								|| args[1].equalsIgnoreCase("edit") || args[1].equalsIgnoreCase("remove")
								|| args[1].equalsIgnoreCase("settings") || args[1].equalsIgnoreCase("help")
								|| args[1].equalsIgnoreCase("settings") || args[1].equalsIgnoreCase("list")
								|| args[1].equalsIgnoreCase("mylist")) {
							player.sendMessage("§cthis is an build in command at §3/warp");
							player.sendMessage("§cyou can't do anything whith this");
							return false;
						}

						// warp remove [warp name]

						// checks if exist
						if (!(getConfig().contains("warps." + args[1]))) {
							player.sendMessage(getConfig().getString("settings.DontExist")
									.replaceAll("/player/", player.getName()).replaceAll("/warp/", args[1]));
							return false;
						}

						// checks if owner
						if (!(player.getName()
								.equalsIgnoreCase(getConfig().getString("warps." + args[1] + ".owner")))) {
							player.sendMessage(getConfig().getString("settings.NotOwner")
									.replaceAll("/player/", player.getName()).replaceAll("/warp/", args[1])
									.replaceAll("/owner/", getConfig().getString("warps." + args[1] + ".owner")));
							return false;
						}

						// removes the warp
						getConfig().set("warps." + args[1], null);
						player.sendMessage(getConfig().getString("settings.RemoveWarp")
								.replaceAll("/player/", player.getName()).replaceAll("/warp/", args[1]));

						ArrayList<String> mylist = (ArrayList<String>) getConfig()
								.getStringList("mylist." + player.getName());
						mylist.remove(args[1]);
						getConfig().set("mylist." + player.getName(), mylist);

						// saves config
						saveConfig();

					} else {
						player.sendMessage(
								getConfig().getString("settings.WrongUse").replaceAll("/player/", player.getName()));
					}
					//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// edit/////////////////////////////////
				} else if (args[0].equalsIgnoreCase("edit")) {
					if (args.length == 2) {
						if (args[1].equalsIgnoreCase("add") || args[1].equalsIgnoreCase("whitelist")
								|| args[1].equalsIgnoreCase("edit") || args[1].equalsIgnoreCase("remove")
								|| args[1].equalsIgnoreCase("settings") || args[1].equalsIgnoreCase("help")
								|| args[1].equalsIgnoreCase("settings") || args[1].equalsIgnoreCase("list")
								|| args[1].equalsIgnoreCase("mylist")) {
							player.sendMessage("§cthis is an build in command at §3/warp");
							player.sendMessage("§cyou can't do anything whith this");
							return false;
						}

						// checks if exist
						if (!(getConfig().contains("warps." + args[1]))) {
							player.sendMessage(getConfig().getString("settings.DontExist")
									.replaceAll("/player/", player.getName()).replaceAll("/warp/", args[1]));
							return false;
						}
						// checks if owner
						if (!(player.getName()
								.equalsIgnoreCase(getConfig().getString("warps." + args[1] + ".owner")))) {
							player.sendMessage(getConfig().getString("settings.NotOwner")
									.replaceAll("/player/", player.getName()).replaceAll("/warp/", args[1])
									.replaceAll("/owner/", getConfig().getString("warps." + args[1] + ".owner")));
							return false;
						}

						// edits the warp
						getConfig().set("warps." + args[1] + ".X", player.getLocation().getX());
						getConfig().set("warps." + args[1] + ".Y", player.getLocation().getY());
						getConfig().set("warps." + args[1] + ".Z", player.getLocation().getZ());
						getConfig().set("warps." + args[1] + ".pitch", player.getLocation().getPitch());
						getConfig().set("warps." + args[1] + ".yaw", player.getLocation().getYaw());
						getConfig().set("warps." + args[1] + ".world", player.getLocation().getWorld().getName());
						player.sendMessage(getConfig().getString("settings.Edited")
								.replaceAll("/player/", player.getName()).replaceAll("/warp/", args[1]));
						// saves config
						saveConfig();

					} else if (args.length == 3) {
						if (args[1].equalsIgnoreCase("add") || args[1].equalsIgnoreCase("whitelist")
								|| args[1].equalsIgnoreCase("edit") || args[1].equalsIgnoreCase("remove")
								|| args[1].equalsIgnoreCase("settings") || args[1].equalsIgnoreCase("help")
								|| args[1].equalsIgnoreCase("settings") || args[1].equalsIgnoreCase("list")
								|| args[1].equalsIgnoreCase("mylist")) {
							player.sendMessage("§cthis is an build in command at §3/warp");
							player.sendMessage("§cyou can't do anything whith this");
							return false;
						}
						// checks if exist
						if (!(getConfig().contains("warps." + args[1]))) {
							player.sendMessage(getConfig().getString("settings.DontExist")
									.replaceAll("/player/", player.getName()).replaceAll("/warp/", args[1]));
							return false;
						}
						// checks if owner
						if (!(player.getName()
								.equalsIgnoreCase(getConfig().getString("warps." + args[1] + ".owner")))) {
							player.sendMessage(getConfig().getString("settings.NotOwner")
									.replaceAll("/player/", player.getName()).replaceAll("/warp/", args[1])
									.replaceAll("/owner/", getConfig().getString("warps." + args[1] + ".owner")));
							return false;
						}

						if (args[2].equalsIgnoreCase("x")) {
							player.sendMessage(getConfig().getString("settings.EditedExtra")
									.replaceAll("/player/", player.getName()).replaceAll("/warp/", args[1])
									.replaceAll("/extra/", args[2]));
							getConfig().set("warps." + args[1] + ".X", player.getLocation().getX());
							saveConfig();
						} else if (args[2].equalsIgnoreCase("y")) {
							player.sendMessage(getConfig().getString("settings.EditedExtra")
									.replaceAll("/player/", player.getName()).replaceAll("/warp/", args[1])
									.replaceAll("/extra/", args[2]));
							getConfig().set("warps." + args[1] + ".Y", player.getLocation().getY());
							saveConfig();
						} else if (args[2].equalsIgnoreCase("z")) {
							player.sendMessage(getConfig().getString("settings.EditedExtra")
									.replaceAll("/player/", player.getName()).replaceAll("/warp/", args[1])
									.replaceAll("/extra/", args[2]));
							getConfig().set("warps." + args[1] + ".Z", player.getLocation().getZ());
							saveConfig();
						} else if (args[2].equalsIgnoreCase("yaw")) {
							player.sendMessage(getConfig().getString("settings.EditedExtra")
									.replaceAll("/player/", player.getName()).replaceAll("/warp/", args[1])
									.replaceAll("/extra/", args[2]));
							getConfig().set("warps." + args[1] + ".yaw", player.getLocation().getYaw());
							saveConfig();
						} else if (args[2].equalsIgnoreCase("pitch")) {
							player.sendMessage(getConfig().getString("settings.EditedExtra")
									.replaceAll("/player/", player.getName()).replaceAll("/warp/", args[1])
									.replaceAll("/extra/", args[2]));
							getConfig().set("warps." + args[1] + ".pitch", player.getLocation().getPitch());
							saveConfig();
						} else if (args[2].equalsIgnoreCase("world")) {
							player.sendMessage(getConfig().getString("settings.EditedExtra")
									.replaceAll("/player/", player.getName()).replaceAll("/warp/", args[1])
									.replaceAll("/extra/", args[2]));
							getConfig().set("warps." + args[1] + ".world", player.getLocation().getWorld().getName());
							saveConfig();
						} else {
							player.sendMessage(getConfig().getString("settings.WrongUse").replaceAll("/player/",
									player.getName()));
						}

					} else if (args.length == 4) {
						if (args[1].equalsIgnoreCase("add") || args[1].equalsIgnoreCase("whitelist")
								|| args[1].equalsIgnoreCase("edit") || args[1].equalsIgnoreCase("remove")
								|| args[1].equalsIgnoreCase("settings") || args[1].equalsIgnoreCase("help")
								|| args[1].equalsIgnoreCase("settings") || args[1].equalsIgnoreCase("list")
								|| args[1].equalsIgnoreCase("mylist")) {
							player.sendMessage("§cthis is an build in command at §3/warp");
							player.sendMessage("§cyou can't do anything whith this");
							return false;
						}
						// checks if exist
						if (!(getConfig().contains("warps." + args[1]))) {
							player.sendMessage(getConfig().getString("settings.DontExist")
									.replaceAll("/player/", player.getName()).replaceAll("/warp/", args[1]));
							return false;
						}
						// checks if owner
						if (!(player.getName()
								.equalsIgnoreCase(getConfig().getString("warps." + args[1] + ".owner")))) {
							player.sendMessage(getConfig().getString("settings.NotOwner")
									.replaceAll("/player/", player.getName()).replaceAll("/warp/", args[1])
									.replaceAll("/owner/", getConfig().getString("warps." + args[1] + ".owner")));
							return false;
						}

						// checks if online
						if (args[2].equalsIgnoreCase("makeowner")) {

							player = Bukkit.getPlayerExact(args[3]);
							if (player == null) {
								sender.sendMessage(getConfig().getString("settings.NotOnline")
										.replaceAll("/player/", sender.getName())
										.replaceAll("/offlineplayer/", args[3]));
								return false;
							}

							if (player.getName().equalsIgnoreCase(args[3])) {
								player.sendMessage(getConfig().getString("settings.YouAreOwner")
										.replaceAll("/player/", player.getName()).replaceAll("/warp/", args[1]));
								return false;
							}

							// sets new owner
							getConfig().set("warps." + args[1] + ".owner", args[3]);
							player.sendMessage(
									getConfig().getString("settings.NewOwner").replaceAll("/player/", player.getName())
											.replaceAll("/warp/", args[1]).replaceAll("/newowner/", args[3]));

							ArrayList<String> mylistremove = (ArrayList<String>) getConfig()
									.getStringList("mylist." + player.getName());
							mylistremove.remove(args[1]);
							getConfig().set("mylist." + player.getName(), mylistremove);

							ArrayList<String> mylist = (ArrayList<String>) getConfig()
									.getStringList("mylist." + args[3]);
							mylist.add(args[1]);
							getConfig().set("mylist." + args[3], mylist);

							saveConfig();
						} else if (args[2].equalsIgnoreCase("private")) {
							if (args[3].equalsIgnoreCase("true")) {
								getConfig().set("warps." + args[1] + ".private", true);
								player.sendMessage(getConfig().getString("settings.ToPrivate")
										.replaceAll("/player/", player.getName()).replaceAll("/warp/", args[1]));
								saveConfig();
							} else if (args[3].equalsIgnoreCase("false")) {
								getConfig().set("warps." + args[1] + ".private", false);
								player.sendMessage(getConfig().getString("settings.ToOpen")
										.replaceAll("/player/", player.getName()).replaceAll("/warp/", args[1]));
								saveConfig();
							} else {
								player.sendMessage(getConfig().getString("settings.TrueFalse")
										.replaceAll("/player/", player.getName()).replaceAll("/warp/", args[1]));
							}
						}

					} else {
						player.sendMessage(
								getConfig().getString("settings.WrongUse").replaceAll("/player/", player.getName()));
					}
					//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// whitelist///////////////////////////////////////////
				} else if (args[0].equalsIgnoreCase("whitelist")) {
					if (args.length == 3) {
						if (args[1].equalsIgnoreCase("add") || args[1].equalsIgnoreCase("whitelist")
								|| args[1].equalsIgnoreCase("edit") || args[1].equalsIgnoreCase("remove")
								|| args[1].equalsIgnoreCase("settings") || args[1].equalsIgnoreCase("help")
								|| args[1].equalsIgnoreCase("settings") || args[1].equalsIgnoreCase("list")
								|| args[1].equalsIgnoreCase("mylist")) {
							player.sendMessage("§cthis is an build in command at §3/warp");
							player.sendMessage("§cyou can't do anything whith this");
							return false;
						}

						if (args[2].equalsIgnoreCase("list")) {

							// checks if exist
							if (!(getConfig().contains("warps." + args[1]))) {
								player.sendMessage(getConfig().getString("settings.DontExist")
										.replaceAll("/player/", player.getName()).replaceAll("/warp/", args[1]));
								return false;
							}
							// checks if owner
							if (!(player.getName()
									.equalsIgnoreCase(getConfig().getString("warps." + args[1] + ".owner")))) {
								player.sendMessage(getConfig().getString("settings.NotOwner")
										.replaceAll("/player/", player.getName()).replaceAll("/warp/", args[1])
										.replaceAll("/owner/", getConfig().getString("warps." + args[1] + ".owner")));
								return false;
							}
							ArrayList<String> whitelist = (ArrayList<String>) getConfig()
									.getStringList("warps." + args[1] + ".whitelist");

							StringBuilder str = new StringBuilder();
							str.append(whitelist.size());
							String whitelistsize = str.toString();

							if (whitelistsize.equals("0")) {
								player.sendMessage(getConfig().getString("settings.ListWhitelistEmpty")
										.replaceAll("/player/", player.getName()).replaceAll("/warp/", args[1]));
								return false;
							}

							player.sendMessage(getConfig().getString("settings.ListWhitelistMessage")
									.replaceAll("/player/", player.getName()).replaceAll("/warp/", args[1])
									.replaceAll("/size/", whitelistsize));

							for (String s : whitelist) {
								player.sendMessage(getConfig().getString("settings.ListWhitelist")
										.replaceAll("/player/", player.getName()).replaceAll("/warp/", args[1])
										.replaceAll("/list/", s));
							}
						} else {
							player.sendMessage(getConfig().getString("settings.WrongUse").replaceAll("/player/",
									player.getName()));
						}
					} else if (args.length == 4) {
						if (args[1].equalsIgnoreCase("add") || args[1].equalsIgnoreCase("whitelist")
								|| args[1].equalsIgnoreCase("edit") || args[1].equalsIgnoreCase("remove")
								|| args[1].equalsIgnoreCase("settings") || args[1].equalsIgnoreCase("help")
								|| args[1].equalsIgnoreCase("settings") || args[1].equalsIgnoreCase("list")
								|| args[1].equalsIgnoreCase("mylist")) {
							player.sendMessage("§cthis is an build in command at §3/warp");
							player.sendMessage("§cyou can't do anything whith this");
							return false;
						}
						if (args[2].equalsIgnoreCase("add")) {

							// checks if exist
							if (!(getConfig().contains("warps." + args[1]))) {
								player.sendMessage(getConfig().getString("settings.DontExist")
										.replaceAll("/player/", player.getName()).replaceAll("/warp/", args[1]));
								return false;
							}

							// checks if owner
							if (!(player.getName()
									.equalsIgnoreCase(getConfig().getString("warps." + args[1] + ".owner")))) {
								player.sendMessage(getConfig().getString("settings.NotOwner")
										.replaceAll("/player/", player.getName()).replaceAll("/warp/", args[1])
										.replaceAll("/owner/", getConfig().getString("warps." + args[1] + ".owner")));
								return false;
							}

							player = Bukkit.getPlayerExact(args[3]);
							if (player == null) {
								sender.sendMessage(getConfig().getString("settings.NotOnline")
										.replaceAll("/player/", sender.getName())
										.replaceAll("/offlineplayer", args[3]));
								return false;
							}

							ArrayList<String> whitelist = (ArrayList<String>) getConfig()
									.getStringList("warps." + args[1] + ".whitelist");

							// String NewPlayer = "";
							// for (int i = 3; i < args.length; i++) {
							// NewPlayer = NewPlayer + args[i] + " ";
							// }
							// NewPlayer = NewPlayer.trim();
							// whitelist.add(NewPlayer);

							if (whitelist.contains(args[3])) {
								player.sendMessage(getConfig().getString("settings.ListWhitelistAlreadyExist")
										.replaceAll("/player/", player.getName()).replaceAll("/warp/", args[1])
										.replaceAll("/addplayer/", args[3]));
								return false;
							}

							whitelist.add(args[3]);

							getConfig().set("warps." + args[1] + ".whitelist", whitelist);

							saveConfig();

							player.sendMessage(getConfig().getString("settings.AddWhitelist")
									.replaceAll("/player/", player.getName()).replaceAll("/warp/", args[1])
									.replaceAll("/newplayer/", args[3]));

						} else if (args[2].equalsIgnoreCase("remove")) {

							// checks if exist
							if (!(getConfig().contains("warps." + args[1]))) {
								player.sendMessage(getConfig().getString("settings.DontExist")
										.replaceAll("/player/", player.getName()).replaceAll("/warp/", args[1]));
								return false;
							}

							// checks if owner
							if (!(player.getName()
									.equalsIgnoreCase(getConfig().getString("warps." + args[1] + ".owner")))) {
								player.sendMessage(getConfig().getString("settings.NotOwner")
										.replaceAll("/player/", player.getName()).replaceAll("/warp/", args[1])
										.replaceAll("/owner/", getConfig().getString("warps." + args[1] + ".owner")));
								return false;
							}

							ArrayList<String> whitelist = (ArrayList<String>) getConfig()
									.getStringList("warps." + args[1] + ".whitelist");

							// String NewPlayer = "";
							// for (int i = 3; i < args.length; i++) {
							// NewPlayer = NewPlayer + args[i] + " ";
							// }
							// NewPlayer = NewPlayer.trim();
							// whitelist.remove(NewPlayer);

							if (!(whitelist.contains(args[3]))) {
								player.sendMessage(getConfig().getString("settings.ListWhitelistDontExist")
										.replaceAll("/player/", player.getName()).replaceAll("/warp/", args[1])
										.replaceAll("/removeplayer/", args[3]));
								return false;
							}

							whitelist.remove(args[3]);

							getConfig().set("warps." + args[1] + ".whitelist", whitelist);

							saveConfig();

							player.sendMessage(getConfig().getString("settings.RemoveWhitelist")
									.replaceAll("/player/", player.getName()).replaceAll("/warp/", args[1])
									.replaceAll("/oldplayer/", args[3]));

						} else {
							player.sendMessage(getConfig().getString("settings.WrongUse").replaceAll("/player/",
									player.getName()));
						}
					} else {
						player.sendMessage(
								getConfig().getString("settings.WrongUse").replaceAll("/player/", player.getName()));
					}
					//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// help
				} else if (args[0].equalsIgnoreCase("help")) {
					if (args.length == 1) {

						player.sendMessage("§euse the following commands");
						player.sendMessage("§3/warp [warp name]");
						player.sendMessage("§3/warp add [warp name]");
						player.sendMessage("§3/warp add [warp name] true");
						player.sendMessage("§3/warp add [warp name] false");
						player.sendMessage("§3/warp remove [warp name]");
						player.sendMessage("§3/warp edit [warp name]");
						player.sendMessage("§3/warp edit [warp name] X/Y/Z/pitch/yaw/world");
						player.sendMessage("§3/warp edit [warp name] makeowner [player name]");
						player.sendMessage("§3/warp edit [warp name] private true");
						player.sendMessage("§3/warp edit [warp name] private false");
						player.sendMessage("§3/warp whitelist [warp name] add [player name]");
						player.sendMessage("§3/warp whitelist [warp name] remove [player name]");
						player.sendMessage("§3/warp whitelist [warp name] list");
						player.sendMessage("§3/warp help");
						if (player.isOp()) {
							player.sendMessage("§3/warp settings");
							player.sendMessage("§3/warp settings help");
						}
						player.sendMessage("§3/warp list");
						player.sendMessage("§3/warp mylist");
						player.sendMessage("§3/warp info [warp name]");
					} else {
						player.sendMessage(
								getConfig().getString("settings.WrongUse").replaceAll("/player/", player.getName()));
					}
					//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// settings
				} else if (args[0].equalsIgnoreCase("settings")) {

					if (!(player.isOp())) {
						player.sendMessage(
								getConfig().getString("settings.NotOP").replaceAll("/player/", player.getName()));
						return false;
					}

					if (args.length == 1) {

						player.sendMessage("§ethese are all the messages");
						player.sendMessage("§3message§6 'WrongUse'§r: " + getConfig().getString("settings.WrongUse"));
						player.sendMessage("§3message§6 'Exist'§r: " + getConfig().getString("settings.Exist"));
						player.sendMessage("§3message§6 'DontExist'§r: " + getConfig().getString("settings.DontExist"));
						player.sendMessage("§3message§6 'AddWarp'§r: " + getConfig().getString("settings.AddWarp"));
						player.sendMessage(
								"§3message§6 'RemoveWarp'§r: " + getConfig().getString("settings.RemoveWarp"));
						player.sendMessage("§3message§6 'Edited'§r: " + getConfig().getString("settings.Edited"));
						player.sendMessage(
								"§3message§6 'EditedExtra'§r: " + getConfig().getString("settings.EditedExtra"));
						player.sendMessage("§3message§6 'NotOnline'§r: " + getConfig().getString("settings.NotOnline"));
						player.sendMessage(
								"§3message§6 'YouAreOwner'§r: " + getConfig().getString("settings.YouAreOwner"));
						player.sendMessage("§3message§6 'NewOwner'§r: " + getConfig().getString("settings.NewOwner"));
						player.sendMessage("§3message§6 'TrueFalse'§r: " + getConfig().getString("settings.TrueFalse"));
						player.sendMessage("§3message§6 'ToPrivate'§r: " + getConfig().getString("settings.ToPrivate"));
						player.sendMessage("§3message§6 'ToOpen'§r: " + getConfig().getString("settings.ToOpen"));
						player.sendMessage("§3message§6 'ListWhitelistEmpty'§r: "
								+ getConfig().getString("settings.ListWhitelistEmpty"));
						player.sendMessage("§3message§6 'ListWhitelistMessage'§r: "
								+ getConfig().getString("settings.ListWhitelistMessage"));
						player.sendMessage(
								"§3message§6 'ListWhitelist'§r: " + getConfig().getString("settings.ListWhitelist"));
						player.sendMessage(
								"§3message§6 'MyListEmpty'§r: " + getConfig().getString("settings.MyListEmpty"));
						player.sendMessage(
								"§3message§6 'MyListMessage'§r: " + getConfig().getString("settings.MyListMessage"));
						player.sendMessage(
								"§3message§6 'MyListList'§r: " + getConfig().getString("settings.MyListList"));
						player.sendMessage(
								"§3message§6 'AddWhitelist'§r: " + getConfig().getString("settings.AddWhitelist"));
						player.sendMessage("§3message§6 'RemoveWhitelist'§r: "
								+ getConfig().getString("settings.RemoveWhitelist"));
						player.sendMessage("§3message§6 'ListWhitelistAlreadyExist'§r: "
								+ getConfig().getString("settings.ListWhitelistAlreadyExist"));
						player.sendMessage("§3message§6 'ListWhitelistDontExist'§r: "
								+ getConfig().getString("settings.ListWhitelistDontExist"));
						player.sendMessage("§3message§6 'NotOwner'§r: " + getConfig().getString("settings.NotOwner"));
						player.sendMessage("§3message§6 'Warped'§r: " + getConfig().getString("settings.Warped"));
						player.sendMessage(
								"§3message§6 'ItsPrivate'§r: " + getConfig().getString("settings.ItsPrivate"));
						player.sendMessage("§3message§6 'NoWorld'§r: " + getConfig().getString("settings.NoWorld"));
						player.sendMessage("§3message§6 'ListWarpMessage'§r: "
								+ getConfig().getString("settings.ListWarpMessage"));
						player.sendMessage("§3message§6 'ListWarp'§r: " + getConfig().getString("settings.ListWarp"));
						player.sendMessage("§3message§6 'Info'§r: " + getConfig().getString("settings.Info"));
						player.sendMessage("§3message§6 'X'§r: " + getConfig().getString("settings.X"));
						player.sendMessage("§3message§6 'Y'§r: " + getConfig().getString("settings.Y"));
						player.sendMessage("§3message§6 'Z'§r: " + getConfig().getString("settings.Z"));
						player.sendMessage("§3message§6 'Yaw'§r: " + getConfig().getString("settings.Yaw"));
						player.sendMessage("§3message§6 'Pitch'§r: " + getConfig().getString("settings.Pitch"));
						player.sendMessage("§3message§6 'World'§r: " + getConfig().getString("settings.World"));
						player.sendMessage("§3message§6 'NotOP'§r: " + getConfig().getString("settings.NotOP"));

					}
					// StringBuilder str = new StringBuilder();
					// for (int i = 1; i < args.length; i++) {
					// str.append(args[i] + " ");
					// }
					//
					// String string = str.toString().replaceAll("&",
					// "§").replaceAll("/player/", args[2])
					// .replaceAll("/warp/", args[1]);
					//
					// player.sendMessage(string);
					//
					// getConfig().set("settings", string);
					//
					// saveConfig();

					else if (args[1].equalsIgnoreCase("WrongUse")) {
						if (args.length == 2) {
							player.sendMessage("§3message§6 'WrongUse'§r: "
									+ getConfig().getString(args[0] + ".WrongUse").replaceAll("&", "§"));
						} else if (args.length == 3) {
							if (args[2].equalsIgnoreCase("extra")) {
								player.sendMessage("§6WrongUse§9 has the extras: §5/warp/");
								player.sendMessage("§5/warp/ §9gives the warp name in the message");
							} else {
								StringBuilder str = new StringBuilder();
								for (int i = 2; i < args.length; i++) {
									str.append(args[i] + " ");
								}
								String string = str.toString().replaceAll("&", "§");
								player.sendMessage(string);
								getConfig().set("settings.WrongUse", string);
								saveConfig();
							}
						} else {
							StringBuilder str = new StringBuilder();
							for (int i = 2; i < args.length; i++) {
								str.append(args[i] + " ");
							}
							String string = str.toString().replaceAll("&", "§");
							player.sendMessage(string);
							getConfig().set("settings.WrongUse", string);
							saveConfig();
						}

					} else if (args[1].equalsIgnoreCase("Exist")) {
						if (args.length == 2) {
							player.sendMessage("§3message§6 'Exist'§r: "
									+ getConfig().getString(args[0] + ".Exist").replaceAll("&", "§"));
						} else if (args.length == 3) {
							if (args[2].equalsIgnoreCase("extra")) {
								player.sendMessage("§6Exist§9 has the extras: §5/player/ §9and §5/warp/");
								player.sendMessage("§5/player/ §9gives the playername in the message");
								player.sendMessage("§5/warp/ §9gives the warp name in the message");
							} else {
								StringBuilder str = new StringBuilder();
								for (int i = 2; i < args.length; i++) {
									str.append(args[i] + " ");
								}
								String string = str.toString().replaceAll("&", "§");
								player.sendMessage(string);
								getConfig().set("settings.Exist", string);
								saveConfig();
							}
						} else {
							StringBuilder str = new StringBuilder();
							for (int i = 2; i < args.length; i++) {
								str.append(args[i] + " ");
							}
							String string = str.toString().replaceAll("&", "§");
							player.sendMessage(string);
							getConfig().set("settings.Exist", string);
							saveConfig();
						}
					} else if (args[1].equalsIgnoreCase("DontExist")) {
						if (args.length == 2) {
							player.sendMessage("§3message§6 'DontExist'§r: "
									+ getConfig().getString(args[0] + ".DontExist").replaceAll("&", "§"));
						} else if (args.length == 3) {
							if (args[2].equalsIgnoreCase("extra")) {
								player.sendMessage("§6DontExist§9 has the extras: §5/player/ §9and §5/warp/");
								player.sendMessage("§5/player/ §9gives the playername in the message");
								player.sendMessage("§5/warp/ §9gives the warp name in the message");
							} else {
								StringBuilder str = new StringBuilder();
								for (int i = 2; i < args.length; i++) {
									str.append(args[i] + " ");
								}
								String string = str.toString().replaceAll("&", "§");
								player.sendMessage(string);
								getConfig().set("settings.DontExist", string);
								saveConfig();
							}
						} else {
							StringBuilder str = new StringBuilder();
							for (int i = 2; i < args.length; i++) {
								str.append(args[i] + " ");
							}
							String string = str.toString().replaceAll("&", "§");
							player.sendMessage(string);
							getConfig().set("settings.DontExist", string);
							saveConfig();
						}
					} else if (args[1].equalsIgnoreCase("AddWarp")) {
						if (args.length == 2) {
							player.sendMessage("§3message§6 'AddWarp'§r: "
									+ getConfig().getString(args[0] + ".AddWarp").replaceAll("&", "§"));
						} else if (args.length == 3) {
							if (args[2].equalsIgnoreCase("extra")) {
								player.sendMessage("§6AddWarp§9 has the extras: §5/player/ §9and §5/warp/");
								player.sendMessage("§5/player/ §9gives the playername in the message");
								player.sendMessage("§5/warp/ §9gives the warp name in the message");
							} else {
								StringBuilder str = new StringBuilder();
								for (int i = 2; i < args.length; i++) {
									str.append(args[i] + " ");
								}
								String string = str.toString().replaceAll("&", "§");
								player.sendMessage(string);
								getConfig().set("settings.AddWarp", string);
								saveConfig();
							}
						} else {
							StringBuilder str = new StringBuilder();
							for (int i = 2; i < args.length; i++) {
								str.append(args[i] + " ");
							}
							String string = str.toString().replaceAll("&", "§");
							player.sendMessage(string);
							getConfig().set("settings.AddWarp", string);
							saveConfig();
						}
					} else if (args[1].equalsIgnoreCase("RemoveWarp")) {
						if (args.length == 2) {
							player.sendMessage("§3message§6 'RemoveWarp'§r: "
									+ getConfig().getString(args[0] + ".RemoveWarp").replaceAll("&", "§"));
						} else if (args.length == 3) {
							if (args[2].equalsIgnoreCase("extra")) {
								player.sendMessage("§6RemoveWarp§9 has the extras: §5/player/ §9and §5/warp/");
								player.sendMessage("§5/player/ §9gives the playername in the message");
								player.sendMessage("§5/warp/ §9gives the warp name in the message");
							} else {
								StringBuilder str = new StringBuilder();
								for (int i = 2; i < args.length; i++) {
									str.append(args[i] + " ");
								}
								String string = str.toString().replaceAll("&", "§");
								player.sendMessage(string);
								getConfig().set("settings.RemoveWarp", string);
								saveConfig();
							}
						} else {
							StringBuilder str = new StringBuilder();
							for (int i = 2; i < args.length; i++) {
								str.append(args[i] + " ");
							}
							String string = str.toString().replaceAll("&", "§");
							player.sendMessage(string);
							getConfig().set("settings.RemoveWarp", string);
							saveConfig();
						}
					} else if (args[1].equalsIgnoreCase("Edited")) {
						if (args.length == 2) {
							player.sendMessage("§3message§6 'Edited'§r: "
									+ getConfig().getString(args[0] + ".Edited").replaceAll("&", "§"));
						} else if (args.length == 3) {
							if (args[2].equalsIgnoreCase("extra")) {
								player.sendMessage("§6Edited§9 has the extras: §5/player/ §9and §5/warp/");
								player.sendMessage("§5/player/ §9gives the playername in the message");
								player.sendMessage("§5/warp/ §9gives the warp name in the message");
							} else {
								StringBuilder str = new StringBuilder();
								for (int i = 2; i < args.length; i++) {
									str.append(args[i] + " ");
								}
								String string = str.toString().replaceAll("&", "§");
								player.sendMessage(string);
								getConfig().set("settings.Edited", string);
								saveConfig();
							}
						} else {
							StringBuilder str = new StringBuilder();
							for (int i = 2; i < args.length; i++) {
								str.append(args[i] + " ");
							}
							String string = str.toString().replaceAll("&", "§");
							player.sendMessage(string);
							getConfig().set("settings.Edited", string);
							saveConfig();
						}
					} else if (args[1].equalsIgnoreCase("EditedExtra")) {
						if (args.length == 2) {
							player.sendMessage("§3message§6 'EditedExtra'§r: "
									+ getConfig().getString(args[0] + ".EditedExtra").replaceAll("&", "§"));
						} else if (args.length == 3) {
							if (args[2].equalsIgnoreCase("extra")) {
								player.sendMessage(
										"§6EditedExtra§9 has the extras: §5/player/§9, §5/warp/ §9and §5/extra/");
								player.sendMessage("§5/player/ §9gives the playername in the message");
								player.sendMessage("§5/warp/ §9gives the warp name in the message");
								player.sendMessage("§5/extra/ §9gives the new value in the message");
							} else {
								StringBuilder str = new StringBuilder();
								for (int i = 2; i < args.length; i++) {
									str.append(args[i] + " ");
								}
								String string = str.toString().replaceAll("&", "§");
								player.sendMessage(string);
								getConfig().set("settings.EditedExtra", string);
								saveConfig();
							}
						} else {
							StringBuilder str = new StringBuilder();
							for (int i = 2; i < args.length; i++) {
								str.append(args[i] + " ");
							}
							String string = str.toString().replaceAll("&", "§");
							player.sendMessage(string);
							getConfig().set("settings.EditedExtra", string);
							saveConfig();
						}
					} else if (args[1].equalsIgnoreCase("NotOnline")) {
						if (args.length == 2) {
							player.sendMessage("§3message§6 'NotOnline'§r: "
									+ getConfig().getString(args[0] + ".NotOnline").replaceAll("&", "§"));
						} else if (args.length == 3) {
							if (args[2].equalsIgnoreCase("extra")) {
								player.sendMessage("§6NotOnline§9 has the extras: §5/player/ §9and /offlineplayer/");
								player.sendMessage("§5/player/ §9gives the playername in the message");
								player.sendMessage("§5/offlineplayer/ §9gives the offline playername in the message");
							} else {
								StringBuilder str = new StringBuilder();
								for (int i = 2; i < args.length; i++) {
									str.append(args[i] + " ");
								}
								String string = str.toString().replaceAll("&", "§");
								player.sendMessage(string);
								getConfig().set("settings.NotOnline", string);
								saveConfig();
							}
						} else {
							StringBuilder str = new StringBuilder();
							for (int i = 2; i < args.length; i++) {
								str.append(args[i] + " ");
							}
							String string = str.toString().replaceAll("&", "§");
							player.sendMessage(string);
							getConfig().set("settings.NotOnline", string);
							saveConfig();
						}
					} else if (args[1].equalsIgnoreCase("YourAreOwner")) {
						if (args.length == 2) {
							player.sendMessage("§3message§6 'YourAreOwner'§r: "
									+ getConfig().getString(args[0] + ".YourAreOwner").replaceAll("&", "§"));
						} else if (args.length == 3) {
							if (args[2].equalsIgnoreCase("extra")) {
								player.sendMessage("§6YourAreOwner§9 has the extras: §5/player/");
								player.sendMessage("§5/player/ §9gives the playername in the message");
							} else {
								StringBuilder str = new StringBuilder();
								for (int i = 2; i < args.length; i++) {
									str.append(args[i] + " ");
								}
								String string = str.toString().replaceAll("&", "§");
								player.sendMessage(string);
								getConfig().set("settings.YourAreOwner", string);
								saveConfig();
							}
						} else {
							StringBuilder str = new StringBuilder();
							for (int i = 2; i < args.length; i++) {
								str.append(args[i] + " ");
							}
							String string = str.toString().replaceAll("&", "§");
							player.sendMessage(string);
							getConfig().set("settings.YourAreOwner", string);
							saveConfig();
						}
					} else if (args[1].equalsIgnoreCase("NewOwner")) {
						if (args.length == 2) {
							player.sendMessage("§3message§6 'NewOwner'§r: "
									+ getConfig().getString(args[0] + ".NewOwner").replaceAll("&", "§"));
						} else if (args.length == 3) {
							if (args[2].equalsIgnoreCase("extra")) {
								player.sendMessage(
										"§6NewOwner§9 has the extras: §5/player/§9, §5/warp/ §9and §5/newowner/");
								player.sendMessage("§5/player/ §9gives the playername in the message");
								player.sendMessage("§5/warp/ §9gives the warp name in the message");
								player.sendMessage("§5/newowner/ §9gives the new owner in the message");
							} else {
								StringBuilder str = new StringBuilder();
								for (int i = 2; i < args.length; i++) {
									str.append(args[i] + " ");
								}
								String string = str.toString().replaceAll("&", "§");
								player.sendMessage(string);
								getConfig().set("settings.NewOwner", string);
								saveConfig();
							}
						} else {
							StringBuilder str = new StringBuilder();
							for (int i = 2; i < args.length; i++) {
								str.append(args[i] + " ");
							}
							String string = str.toString().replaceAll("&", "§");
							player.sendMessage(string);
							getConfig().set("settings.NewOwner", string);
							saveConfig();
						}
					} else if (args[1].equalsIgnoreCase("TrueFalse")) {
						if (args.length == 2) {
							player.sendMessage("§3message§6 'TrueFalse'§r: "
									+ getConfig().getString(args[0] + ".TrueFalse").replaceAll("&", "§"));
						} else if (args.length == 3) {
							if (args[2].equalsIgnoreCase("extra")) {
								player.sendMessage("§6TrueFalse§9 has the extras: §5/player/ §9and §5/warp/");
								player.sendMessage("§5/player/ §9gives the playername in the message");
								player.sendMessage("§5/warp/ §9gives the warp name in the message");
							} else {
								StringBuilder str = new StringBuilder();
								for (int i = 2; i < args.length; i++) {
									str.append(args[i] + " ");
								}
								String string = str.toString().replaceAll("&", "§");
								player.sendMessage(string);
								getConfig().set("settings.TrueFalse", string);
								saveConfig();
							}
						} else {
							StringBuilder str = new StringBuilder();
							for (int i = 2; i < args.length; i++) {
								str.append(args[i] + " ");
							}
							String string = str.toString().replaceAll("&", "§");
							player.sendMessage(string);
							getConfig().set("settings.TrueFalse", string);
							saveConfig();
						}
					} else if (args[1].equalsIgnoreCase("ToPrivate")) {
						if (args.length == 2) {
							player.sendMessage("§3message§6 'ToPrivate'§r: "
									+ getConfig().getString(args[0] + ".ToPrivate").replaceAll("&", "§"));
						} else if (args.length == 3) {
							if (args[2].equalsIgnoreCase("extra")) {
								player.sendMessage("§6TPrivate§9 has the extras: §5/player/ §9and §5/warp/");
								player.sendMessage("§5/player/ §9gives the playername in the message");
								player.sendMessage("§5/warp/ §9gives the warp name in the message");
							} else {
								StringBuilder str = new StringBuilder();
								for (int i = 2; i < args.length; i++) {
									str.append(args[i] + " ");
								}
								String string = str.toString().replaceAll("&", "§");
								player.sendMessage(string);
								getConfig().set("settings.ToPrivate", string);
								saveConfig();
							}
						} else {
							StringBuilder str = new StringBuilder();
							for (int i = 2; i < args.length; i++) {
								str.append(args[i] + " ");
							}
							String string = str.toString().replaceAll("&", "§");
							player.sendMessage(string);
							getConfig().set("settings.ToPrivate", string);
							saveConfig();
						}
					} else if (args[1].equalsIgnoreCase("ToOpen")) {
						if (args.length == 2) {
							player.sendMessage("§3message§6 'ToOpen'§r: "
									+ getConfig().getString(args[0] + ".ToOpen").replaceAll("&", "§"));
						} else if (args.length == 3) {
							if (args[2].equalsIgnoreCase("extra")) {
								player.sendMessage("§6ToOpen§9 has the extras: §5/player/ §9and §5/warp/");
								player.sendMessage("§5/player/ §9gives the playername in the message");
								player.sendMessage("§5/warp/ §9gives the warp name in the message");
							} else {
								StringBuilder str = new StringBuilder();
								for (int i = 2; i < args.length; i++) {
									str.append(args[i] + " ");
								}
								String string = str.toString().replaceAll("&", "§");
								player.sendMessage(string);
								getConfig().set("settings.ToOpen", string);
								saveConfig();
							}
						} else {
							StringBuilder str = new StringBuilder();
							for (int i = 2; i < args.length; i++) {
								str.append(args[i] + " ");
							}
							String string = str.toString().replaceAll("&", "§");
							player.sendMessage(string);
							getConfig().set("settings.ToOpen", string);
							saveConfig();
						}
					} else if (args[1].equalsIgnoreCase("ListWhitelistEmpty")) {
						if (args.length == 2) {
							player.sendMessage("§3message§6 'ListWhitelistEmpty'§r: "
									+ getConfig().getString(args[0] + ".ListWhitelistEmpty").replaceAll("&", "§"));
						} else if (args.length == 3) {
							if (args[2].equalsIgnoreCase("extra")) {
								player.sendMessage("§6ListWhitelistEmpty§9 has the extras: §5/player/ §9and §5/warp/");
								player.sendMessage("§5/player/ §9gives the playername in the message");
								player.sendMessage("§5/warp/ §9gives the warp name in the message");
							} else {
								StringBuilder str = new StringBuilder();
								for (int i = 2; i < args.length; i++) {
									str.append(args[i] + " ");
								}
								String string = str.toString().replaceAll("&", "§");
								player.sendMessage(string);
								getConfig().set("settings.ListWhitelistEmpty", string);
								saveConfig();
							}
						} else {
							StringBuilder str = new StringBuilder();
							for (int i = 2; i < args.length; i++) {
								str.append(args[i] + " ");
							}
							String string = str.toString().replaceAll("&", "§");
							player.sendMessage(string);
							getConfig().set("settings.ListWhitelistEmpty", string);
							saveConfig();
						}
					} else if (args[1].equalsIgnoreCase("ListWhitelistMessage")) {
						if (args.length == 2) {
							player.sendMessage("§3message§6 'ListWhitelistMessage'§r: "
									+ getConfig().getString(args[0] + ".ListWhitelistMessage").replaceAll("&", "§"));
						} else if (args.length == 3) {
							if (args[2].equalsIgnoreCase("extra")) {
								player.sendMessage(
										"§6ListWhitelistMessage§9 has the extras: §5/player/§9, §5/warp/ §9and §5/size/");
								player.sendMessage("§5/player/ §9gives the playername in the message");
								player.sendMessage("§5/warp/ §9gives the warp name in the message");
								player.sendMessage("§5/size/ §9gives the whitelist size in the message");
							} else {
								StringBuilder str = new StringBuilder();
								for (int i = 2; i < args.length; i++) {
									str.append(args[i] + " ");
								}
								String string = str.toString().replaceAll("&", "§");
								player.sendMessage(string);
								getConfig().set("settings.ListWhitelistMessage", string);
								saveConfig();
							}
						} else {
							StringBuilder str = new StringBuilder();
							for (int i = 2; i < args.length; i++) {
								str.append(args[i] + " ");
							}
							String string = str.toString().replaceAll("&", "§");
							player.sendMessage(string);
							getConfig().set("settings.ListWhitelistMessage", string);
							saveConfig();
						}
					} else if (args[1].equalsIgnoreCase("ListWhitelist")) {
						if (args.length == 2) {
							player.sendMessage("§3message§6 'ListWhitelist'§r: "
									+ getConfig().getString(args[0] + ".ListWhitelist").replaceAll("&", "§"));
						} else if (args.length == 3) {
							if (args[2].equalsIgnoreCase("extra")) {
								player.sendMessage(
										"§6ListWhitelist§9 has the extras: §5/player/ §9, §5/warp/ §9and §5/list/");
								player.sendMessage("§5/player/ §9gives the playername in the message");
								player.sendMessage("§5/warp/ §9gives the warp name in the message");
								player.sendMessage("§5/list/ §9gives the players in whitelist in the message");
							} else {
								StringBuilder str = new StringBuilder();
								for (int i = 2; i < args.length; i++) {
									str.append(args[i] + " ");
								}
								String string = str.toString().replaceAll("&", "§");
								player.sendMessage(string);
								getConfig().set("settings.ListWhitelist", string);
								saveConfig();
							}
						} else {
							StringBuilder str = new StringBuilder();
							for (int i = 2; i < args.length; i++) {
								str.append(args[i] + " ");
							}
							String string = str.toString().replaceAll("&", "§");
							player.sendMessage(string);
							getConfig().set("settings.ListWhitelist", string);
							saveConfig();
						}
					} else if (args[1].equalsIgnoreCase("MyListEmpty")) {
						if (args.length == 2) {
							player.sendMessage("§3message§6 'MyListEmpty'§r: "
									+ getConfig().getString(args[0] + ".MyListEmpty").replaceAll("&", "§"));
						} else if (args.length == 3) {
							if (args[2].equalsIgnoreCase("extra")) {
								player.sendMessage("§6MyListEmpty§9 has the extras: §5 /player/");
								player.sendMessage("§5/player/ §9gives the warp name in the message");
							} else {
								StringBuilder str = new StringBuilder();
								for (int i = 2; i < args.length; i++) {
									str.append(args[i] + " ");
								}
								String string = str.toString().replaceAll("&", "§");
								player.sendMessage(string);
								getConfig().set("settings.MyListEmpty", string);
								saveConfig();
							}
						} else {
							StringBuilder str = new StringBuilder();
							for (int i = 2; i < args.length; i++) {
								str.append(args[i] + " ");
							}
							String string = str.toString().replaceAll("&", "§");
							player.sendMessage(string);
							getConfig().set("settings.MyListEmpty", string);
							saveConfig();
						}
					} else if (args[1].equalsIgnoreCase("MyListMessage")) {
						if (args.length == 2) {
							player.sendMessage("§3message§6 'MyListMessage'§r: "
									+ getConfig().getString(args[0] + ".MyListMessage").replaceAll("&", "§"));
						} else if (args.length == 3) {
							if (args[2].equalsIgnoreCase("extra")) {
								player.sendMessage("§6MyListMessage§9 has the extras: §5/player/ §9and §5/size/");
								player.sendMessage("§5/player/ §9gives the playername in the message");
								player.sendMessage("§5/size/ §9gives your list size in the message");
							} else {
								StringBuilder str = new StringBuilder();
								for (int i = 2; i < args.length; i++) {
									str.append(args[i] + " ");
								}
								String string = str.toString().replaceAll("&", "§");
								player.sendMessage(string);
								getConfig().set("settings.MyListMessage", string);
								saveConfig();
							}
						} else {
							StringBuilder str = new StringBuilder();
							for (int i = 2; i < args.length; i++) {
								str.append(args[i] + " ");
							}
							String string = str.toString().replaceAll("&", "§");
							player.sendMessage(string);
							getConfig().set("settings.MyListMessage", string);
							saveConfig();
						}
					} else if (args[1].equalsIgnoreCase("MyListList")) {
						if (args.length == 2) {
							player.sendMessage("§3message§6 'MyListList'§r: "
									+ getConfig().getString(args[0] + ".MyListList").replaceAll("&", "§"));
						} else if (args.length == 3) {
							if (args[2].equalsIgnoreCase("extra")) {
								player.sendMessage("§6MyListList§9 has the extras: §5/player/ §9and §5/list/");
								player.sendMessage("§5/player/ §9gives the playername in the message");
								player.sendMessage("§5/list/ §9gives the warps of your list in the message");
							} else {
								StringBuilder str = new StringBuilder();
								for (int i = 2; i < args.length; i++) {
									str.append(args[i] + " ");
								}
								String string = str.toString().replaceAll("&", "§");
								player.sendMessage(string);
								getConfig().set("settings.MyListList", string);
								saveConfig();
							}
						} else {
							StringBuilder str = new StringBuilder();
							for (int i = 2; i < args.length; i++) {
								str.append(args[i] + " ");
							}
							String string = str.toString().replaceAll("&", "§");
							player.sendMessage(string);
							getConfig().set("settings.MyListList", string);
							saveConfig();
						}
					} else if (args[1].equalsIgnoreCase("AddWhitelist")) {
						if (args.length == 2) {
							player.sendMessage("§3message§6 'AddWhitelist'§r: "
									+ getConfig().getString(args[0] + ".AddWhitelist").replaceAll("&", "§"));
						} else if (args.length == 3) {
							if (args[2].equalsIgnoreCase("extra")) {
								player.sendMessage(
										"§6AddWhitelist§9 has the extras: §5/player/§9, §5/warp/ §9and §5/newplayer/");
								player.sendMessage("§5/player/ §9gives the playername in the message");
								player.sendMessage("§5/warp/ §9gives the warp name in the message");
								player.sendMessage("§5/newplayer/ §9gives the new player in the message");
							} else {
								StringBuilder str = new StringBuilder();
								for (int i = 2; i < args.length; i++) {
									str.append(args[i] + " ");
								}
								String string = str.toString().replaceAll("&", "§");
								player.sendMessage(string);
								getConfig().set("settings.AddWhitelist", string);
								saveConfig();
							}
						} else {
							StringBuilder str = new StringBuilder();
							for (int i = 2; i < args.length; i++) {
								str.append(args[i] + " ");
							}
							String string = str.toString().replaceAll("&", "§");
							player.sendMessage(string);
							getConfig().set("settings.AddWhitelist", string);
							saveConfig();
						}
					} else if (args[1].equalsIgnoreCase("RemoveWhitelist")) {
						if (args.length == 2) {
							player.sendMessage("§3message§6 'RemoveWhitelist'§r "
									+ getConfig().getString(args[0] + ".RemoveWhitelist").replaceAll("&", "§"));
						} else if (args.length == 3) {
							if (args[2].equalsIgnoreCase("extra")) {
								player.sendMessage(
										"§6RemoveWhitelist§9 has the extras: §5/player/§9, §5/warp/ §9and §5/oldplayer/");
								player.sendMessage("§5/player/ §9gives the playername in the message");
								player.sendMessage("§5/warp/ §9gives the warp name in the message");
								player.sendMessage("§5/newplayer/ §9gives the removed player in the message");
							} else {
								StringBuilder str = new StringBuilder();
								for (int i = 2; i < args.length; i++) {
									str.append(args[i] + " ");
								}
								String string = str.toString().replaceAll("&", "§");
								player.sendMessage(string);
								getConfig().set("settings.RemoveWhitelist", string);
								saveConfig();
							}
						} else {
							StringBuilder str = new StringBuilder();
							for (int i = 2; i < args.length; i++) {
								str.append(args[i] + " ");
							}
							String string = str.toString().replaceAll("&", "§");
							player.sendMessage(string);
							getConfig().set("settings.RemoveWhitelist", string);
							saveConfig();
						}
					} else if (args[1].equalsIgnoreCase("ListWhitelistAlreadyExist")) {
						if (args.length == 2) {
							player.sendMessage("§3message§6 'ListWhitelistAlreadyExist'§r " + getConfig()
									.getString(args[0] + ".ListWhitelistAlreadyExist").replaceAll("&", "§"));
						} else if (args.length == 3) {
							if (args[2].equalsIgnoreCase("extra")) {
								player.sendMessage(
										"§6ListWhitelistAlreadyExist§9 has the extras: §5/player/§9, §5/warp/ §9and §5/addplayer/");
								player.sendMessage("§5/player/ §9gives the playername in the message");
								player.sendMessage("§5/warp/ §9gives the warp name in the message");
								player.sendMessage("§5/addplayer/ §9gives the not added player in the message");
							} else {
								StringBuilder str = new StringBuilder();
								for (int i = 2; i < args.length; i++) {
									str.append(args[i] + " ");
								}
								String string = str.toString().replaceAll("&", "§");
								player.sendMessage(string);
								getConfig().set("settings.ListWhitelistAlreadyExist", string);
								saveConfig();
							}
						} else {
							StringBuilder str = new StringBuilder();
							for (int i = 2; i < args.length; i++) {
								str.append(args[i] + " ");
							}
							String string = str.toString().replaceAll("&", "§");
							player.sendMessage(string);
							getConfig().set("settings.ListWhitelistAlreadyExist", string);
							saveConfig();
						}
					} else if (args[1].equalsIgnoreCase("ListWhitelistDontExist")) {
						if (args.length == 2) {
							player.sendMessage("§3message§6 'ListWhitelistDontExist'§r "
									+ getConfig().getString(args[0] + ".ListWhitelistDontExist").replaceAll("&", "§"));
						} else if (args.length == 3) {
							if (args[2].equalsIgnoreCase("extra")) {
								player.sendMessage(
										"§6ListWhitelistDontExist§9 has the extras: §5/player/§9, §5/warp/ §9and §5/removeplayer/");
								player.sendMessage("§5/player/ §9gives the playername in the message");
								player.sendMessage("§5/warp/ §9gives the warp name in the message");
								player.sendMessage("§5/removeplayer/ §9gives the not removed player in the message");
							} else {
								StringBuilder str = new StringBuilder();
								for (int i = 2; i < args.length; i++) {
									str.append(args[i] + " ");
								}
								String string = str.toString().replaceAll("&", "§");
								player.sendMessage(string);
								getConfig().set("settings.ListWhitelistDontExist", string);
								saveConfig();
							}
						} else {
							StringBuilder str = new StringBuilder();
							for (int i = 2; i < args.length; i++) {
								str.append(args[i] + " ");
							}
							String string = str.toString().replaceAll("&", "§");
							player.sendMessage(string);
							getConfig().set("settings.ListWhitelistDontExist", string);
							saveConfig();
						}
					} else if (args[1].equalsIgnoreCase("NotOwner")) {
						if (args.length == 2) {
							player.sendMessage("§3message§6 'NotOwner'§r: "
									+ getConfig().getString(args[0] + ".NotOwner").replaceAll("&", "§"));
						} else if (args.length == 3) {
							if (args[2].equalsIgnoreCase("extra")) {
								player.sendMessage(
										"§6NotOwner§9 has the extras: §5/player/§9, §5/warp/ §9and §5/owner/");
								player.sendMessage("§5/player/ §9gives the playername in the message");
								player.sendMessage("§5/warp/ §9gives the warp name in the message");
								player.sendMessage("§5/owner/ §9gives the owner in the message");
								return false;
							} else {
								StringBuilder str = new StringBuilder();
								for (int i = 2; i < args.length; i++) {
									str.append(args[i] + " ");
								}
								String string = str.toString().replaceAll("&", "§");
								player.sendMessage(string);
								getConfig().set("settings.NotOwner", string);
								saveConfig();
							}
						} else {
							StringBuilder str = new StringBuilder();
							for (int i = 2; i < args.length; i++) {
								str.append(args[i] + " ");
							}
							String string = str.toString().replaceAll("&", "§");
							player.sendMessage(string);
							getConfig().set("settings.NotOwner", string);
							saveConfig();
						}
					} else if (args[1].equalsIgnoreCase("Warped")) {
						if (args.length == 2) {
							player.sendMessage("§3message§6 'Warped'§r: "
									+ getConfig().getString(args[0] + ".Warped").replaceAll("&", "§"));
						} else if (args.length == 3) {
							if (args[2].equalsIgnoreCase("extra")) {
								player.sendMessage("§6Warped§9 has the extras: §5/player/ §9and §5/warp/");
								player.sendMessage("§5/player/ §9gives the playername in the message");
								player.sendMessage("§5/warp/ §9gives the warp name in the message");
							} else {
								StringBuilder str = new StringBuilder();
								for (int i = 2; i < args.length; i++) {
									str.append(args[i] + " ");
								}
								String string = str.toString().replaceAll("&", "§");
								player.sendMessage(string);
								getConfig().set("settings.Warped", string);
								saveConfig();
							}
						} else {
							StringBuilder str = new StringBuilder();
							for (int i = 2; i < args.length; i++) {
								str.append(args[i] + " ");
							}
							String string = str.toString().replaceAll("&", "§");
							player.sendMessage(string);
							getConfig().set("settings.Warped", string);
							saveConfig();
						}
					} else if (args[1].equalsIgnoreCase("ItsPrivate")) {
						if (args.length == 2) {
							player.sendMessage("§3message§6 'ItsPrivate'§r: "
									+ getConfig().getString(args[0] + ".ItsPrivate").replaceAll("&", "§"));
						} else if (args.length == 3) {
							if (args[2].equalsIgnoreCase("extra")) {
								player.sendMessage(
										"§6ItsPrivate§9 has the extras: §5/player/ §9, §5/warp/ §9and §5/owner/");
								player.sendMessage("§5/player/ §9gives the playername in the message");
								player.sendMessage("§5/warp/ §9gives the warp name in the message");
								player.sendMessage("§5/owner/ §9gives the owner in the message");
							} else {
								StringBuilder str = new StringBuilder();
								for (int i = 2; i < args.length; i++) {
									str.append(args[i] + " ");
								}
								String string = str.toString().replaceAll("&", "§");
								player.sendMessage(string);
								getConfig().set("settings.ItsPrivate", string);
								saveConfig();
							}
						} else {
							StringBuilder str = new StringBuilder();
							for (int i = 2; i < args.length; i++) {
								str.append(args[i] + " ");
							}
							String string = str.toString().replaceAll("&", "§");
							player.sendMessage(string);
							getConfig().set("settings.ItsPrivate", string);
							saveConfig();
						}
					} else if (args[1].equalsIgnoreCase("NoWorld")) {
						if (args.length == 2) {
							player.sendMessage("§3message§6 'NoWorld'§r: "
									+ getConfig().getString(args[0] + ".NoWorld").replaceAll("&", "§"));
						} else if (args.length == 3) {
							if (args[2].equalsIgnoreCase("extra")) {
								player.sendMessage("§6NoWorld§9 has the extras: §5/player/ §9and §5/warp/");
								player.sendMessage("§5/player/ §9gives the playername in the message");
								player.sendMessage("§5/warp/ §9gives the warp name in the message");
							} else {
								StringBuilder str = new StringBuilder();
								for (int i = 2; i < args.length; i++) {
									str.append(args[i] + " ");
								}
								String string = str.toString().replaceAll("&", "§");
								player.sendMessage(string);
								getConfig().set("settings.NoWorld", string);
								saveConfig();
							}
						} else {
							StringBuilder str = new StringBuilder();
							for (int i = 2; i < args.length; i++) {
								str.append(args[i] + " ");
							}
							String string = str.toString().replaceAll("&", "§");
							player.sendMessage(string);
							getConfig().set("settings.NoWorld", string);
							saveConfig();
						}
					} else if (args[1].equalsIgnoreCase("ListWarpMessage")) {
						if (args.length == 2) {
							player.sendMessage("§3message§6 'ListWarpMessage'§r: "
									+ getConfig().getString(args[0] + ".ListWarpMessage").replaceAll("&", "§"));
						} else if (args.length == 3) {
							if (args[2].equalsIgnoreCase("extra")) {
								player.sendMessage("§6ListWarpMessage§9 has the extras: §5/player/");
								player.sendMessage("§5/player/ §9gives the playername in the message");
							} else {
								StringBuilder str = new StringBuilder();
								for (int i = 2; i < args.length; i++) {
									str.append(args[i] + " ");
								}
								String string = str.toString().replaceAll("&", "§");
								player.sendMessage(string);
								getConfig().set("settings.ListWarpMessage", string);
								saveConfig();
							}
						} else {
							StringBuilder str = new StringBuilder();
							for (int i = 2; i < args.length; i++) {
								str.append(args[i] + " ");
							}
							String string = str.toString().replaceAll("&", "§");
							player.sendMessage(string);
							getConfig().set("settings.ListWarpMessage", string);
							saveConfig();
						}
					} else if (args[1].equalsIgnoreCase("ListWarp")) {
						if (args.length == 2) {
							player.sendMessage("§3message§6 'ListWarp'§r: "
									+ getConfig().getString(args[0] + ".ListWarp").replaceAll("&", "§"));
						} else if (args.length == 3) {
							if (args[2].equalsIgnoreCase("extra")) {
								player.sendMessage("§6ListWarp§9 has the extras: §5/player/ §9and §5/list/");
								player.sendMessage("§5/player/ §9gives the playername in the message");
								player.sendMessage("§5/list/ §9gives the warps in whitelist in the message");
							} else {
								StringBuilder str = new StringBuilder();
								for (int i = 2; i < args.length; i++) {
									str.append(args[i] + " ");
								}
								String string = str.toString().replaceAll("&", "§");
								player.sendMessage(string);
								getConfig().set("settings.ListWarp", string);
								saveConfig();
							}
						} else {
							StringBuilder str = new StringBuilder();
							for (int i = 2; i < args.length; i++) {
								str.append(args[i] + " ");
							}
							String string = str.toString().replaceAll("&", "§");
							player.sendMessage(string);
							getConfig().set("settings.ListWarp", string);
							saveConfig();
						}
					} else if (args[1].equalsIgnoreCase("Info")) {
						if (args.length == 2) {
							player.sendMessage("§3message§6 'Info'§r: "
									+ getConfig().getString(args[0] + ".Info").replaceAll("&", "§"));
						} else if (args.length == 3) {
							if (args[2].equalsIgnoreCase("extra")) {
								player.sendMessage("§6Info§9 has the extras: §5/player/ §9and §5/warp/");
								player.sendMessage("§5/player/ §9gives the playername in the message");
								player.sendMessage("§5/warp/ §9gives the warp name in the message");
							} else {
								StringBuilder str = new StringBuilder();
								for (int i = 2; i < args.length; i++) {
									str.append(args[i] + " ");
								}
								String string = str.toString().replaceAll("&", "§");
								player.sendMessage(string);
								getConfig().set("settings.Info", string);
								saveConfig();
							}
						} else {
							StringBuilder str = new StringBuilder();
							for (int i = 2; i < args.length; i++) {
								str.append(args[i] + " ");
							}
							String string = str.toString().replaceAll("&", "§");
							player.sendMessage(string);
							getConfig().set("settings.Info", string);
							saveConfig();
						}
					} else if (args[1].equalsIgnoreCase("X")) {
						if (args.length == 2) {
							player.sendMessage(
									"§3message§6 'X'§r: " + getConfig().getString(args[0] + ".X").replaceAll("&", "§"));
						} else if (args.length == 3) {
							if (args[2].equalsIgnoreCase("extra")) {
								player.sendMessage("§6X§9 has the extras: §5/player/ §9, §5/warp/ §9, §5/x/");
								player.sendMessage("§5/player/ §9gives the playername in the message");
								player.sendMessage("§5/warp/ §9gives the warp name in the message");
								player.sendMessage("§5/x/ §9gives the x coords in the message");
							} else {
								StringBuilder str = new StringBuilder();
								for (int i = 2; i < args.length; i++) {
									str.append(args[i] + " ");
								}
								String string = str.toString().replaceAll("&", "§");
								player.sendMessage(string);
								getConfig().set("settings.X", string);
								saveConfig();
							}
						} else {
							StringBuilder str = new StringBuilder();
							for (int i = 2; i < args.length; i++) {
								str.append(args[i] + " ");
							}
							String string = str.toString().replaceAll("&", "§");
							player.sendMessage(string);
							getConfig().set("settings.X", string);
							saveConfig();
						}
					} else if (args[1].equalsIgnoreCase("Y")) {
						if (args.length == 2) {
							player.sendMessage(
									"§3message§6 'Y'§r: " + getConfig().getString(args[0] + ".Y").replaceAll("&", "§"));
						} else if (args.length == 3) {
							if (args[2].equalsIgnoreCase("extra")) {
								player.sendMessage("§6Y§9 has the extras: §5/player/ §9, §5/warp/ §9and §5/y/");
								player.sendMessage("§5/player/ §9gives the playername in the message");
								player.sendMessage("§5/warp/ §9gives the warp name in the message");
								player.sendMessage("§5/y/ §9gives the y coords in the message");
							} else {
								StringBuilder str = new StringBuilder();
								for (int i = 2; i < args.length; i++) {
									str.append(args[i] + " ");
								}
								String string = str.toString().replaceAll("&", "§");
								player.sendMessage(string);
								getConfig().set("settings.Y", string);
								saveConfig();
							}
						} else {
							StringBuilder str = new StringBuilder();
							for (int i = 2; i < args.length; i++) {
								str.append(args[i] + " ");
							}
							String string = str.toString().replaceAll("&", "§");
							player.sendMessage(string);
							getConfig().set("settings.Y", string);
							saveConfig();
						}
					} else if (args[1].equalsIgnoreCase("Z")) {
						if (args.length == 2) {
							player.sendMessage(
									"§3message§6 'Z'§r: " + getConfig().getString(args[0] + ".Z").replaceAll("&", "§"));
						} else if (args.length == 3) {
							if (args[2].equalsIgnoreCase("extra")) {
								player.sendMessage("§6Z§9 has the extras: §5/player/ 9, §/warp/ §9and §5/z/");
								player.sendMessage("§5/player/ §9gives the playername in the message");
								player.sendMessage("§5/warp/ §9gives the warp name in the message");
								player.sendMessage("§5/z/ §9gives the z coords in the message");
							} else {
								StringBuilder str = new StringBuilder();
								for (int i = 2; i < args.length; i++) {
									str.append(args[i] + " ");
								}
								String string = str.toString().replaceAll("&", "§");
								player.sendMessage(string);
								getConfig().set("settings.Z", string);
								saveConfig();
							}
						} else {
							StringBuilder str = new StringBuilder();
							for (int i = 2; i < args.length; i++) {
								str.append(args[i] + " ");
							}
							String string = str.toString().replaceAll("&", "§");
							player.sendMessage(string);
							getConfig().set("settings.Z", string);
							saveConfig();
						}
					} else if (args[1].equalsIgnoreCase("Yaw")) {
						if (args.length == 2) {
							player.sendMessage("§3message§6 'Yaw'§r: "
									+ getConfig().getString(args[0] + ".Yaw").replaceAll("&", "§"));
						} else if (args.length == 3) {
							if (args[2].equalsIgnoreCase("extra")) {
								player.sendMessage("§6Yaw§9 has the extras: §5/player/ §9, §5/warp/ §9and §5/yaw/");
								player.sendMessage("§5/player/ §9gives the playername in the message");
								player.sendMessage("§5/warp/ §9gives the warp name in the message");
								player.sendMessage("§5/yaw/ §9gives the yaw in the message");
							} else {
								StringBuilder str = new StringBuilder();
								for (int i = 2; i < args.length; i++) {
									str.append(args[i] + " ");
								}
								String string = str.toString().replaceAll("&", "§");
								player.sendMessage(string);
								getConfig().set("settings.Yaw", string);
								saveConfig();
							}
						} else {
							StringBuilder str = new StringBuilder();
							for (int i = 2; i < args.length; i++) {
								str.append(args[i] + " ");
							}
							String string = str.toString().replaceAll("&", "§");
							player.sendMessage(string);
							getConfig().set("settings.Yaw", string);
							saveConfig();
						}
					} else if (args[1].equalsIgnoreCase("Pitch")) {
						if (args.length == 2) {
							player.sendMessage("§3message§6 'Pitch'§r: "
									+ getConfig().getString(args[0] + ".Pitch").replaceAll("&", "§"));
						} else if (args.length == 3) {
							if (args[2].equalsIgnoreCase("extra")) {
								player.sendMessage("§6Pitch§9 has the extras: §5/player/ §9, §5/warp/ §9and §5/pitch/");
								player.sendMessage("§5/player/ §9gives the playername in the message");
								player.sendMessage("§5/warp/ §9gives the warp name in the message");
								player.sendMessage("§5/pitch/ §9gives the pitch in the message");
							} else {
								StringBuilder str = new StringBuilder();
								for (int i = 2; i < args.length; i++) {
									str.append(args[i] + " ");
								}
								String string = str.toString().replaceAll("&", "§");
								player.sendMessage(string);
								getConfig().set("settings.Pitch", string);
								saveConfig();
							}
						} else {
							StringBuilder str = new StringBuilder();
							for (int i = 2; i < args.length; i++) {
								str.append(args[i] + " ");
							}
							String string = str.toString().replaceAll("&", "§");
							player.sendMessage(string);
							getConfig().set("settings.Pitch", string);
							saveConfig();
						}
					} else if (args[1].equalsIgnoreCase("World")) {
						if (args.length == 2) {
							player.sendMessage("§3message§6 'World'§r: "
									+ getConfig().getString(args[0] + ".World").replaceAll("&", "§"));
						} else if (args.length == 3) {
							if (args[2].equalsIgnoreCase("extra")) {
								player.sendMessage("§6World§9 has the extras: §5/player/ §9, 5/warp/ §9and §5/world/");
								player.sendMessage("§5/player/ §9gives the playername in the message");
								player.sendMessage("§5/warp/ §9gives the warp name in the message");
								player.sendMessage("§5/world/ §9gives the world name in the message");
							} else {
								StringBuilder str = new StringBuilder();
								for (int i = 2; i < args.length; i++) {
									str.append(args[i] + " ");
								}
								String string = str.toString().replaceAll("&", "§");
								player.sendMessage(string);
								getConfig().set("settings.World", string);
								saveConfig();
							}
						} else {
							StringBuilder str = new StringBuilder();
							for (int i = 2; i < args.length; i++) {
								str.append(args[i] + " ");
							}
							String string = str.toString().replaceAll("&", "§");
							player.sendMessage(string);
							getConfig().set("settings.World", string);
							saveConfig();
						}
					} else if (args[1].equalsIgnoreCase("NotOP")) {
						if (args.length == 2) {
							player.sendMessage("§3message§6 'NotOP'§r: "
									+ getConfig().getString(args[0] + ".NotOP").replaceAll("&", "§"));
						} else if (args.length == 3) {
							if (args[2].equalsIgnoreCase("extra")) {
								player.sendMessage("§6NotOP§9 has the extras: §5/player/");
								player.sendMessage("§5/player/ §9gives the playername in the message");
							} else {
								StringBuilder str = new StringBuilder();
								for (int i = 2; i < args.length; i++) {
									str.append(args[i] + " ");
								}
								String string = str.toString().replaceAll("&", "§");
								player.sendMessage(string);
								getConfig().set("settings.NotOP", string);
								saveConfig();
							}
						} else {
							StringBuilder str = new StringBuilder();
							for (int i = 2; i < args.length; i++) {
								str.append(args[i] + " ");
							}
							String string = str.toString().replaceAll("&", "§");
							player.sendMessage(string);
							getConfig().set("settings.NotOP", string);
							saveConfig();
						}
					} else if (args[1].equalsIgnoreCase("Help")) {
						if (args.length == 2) {
							player.sendMessage(
									"§ethis is the command where you can edit all the messages from this plugin");
							player.sendMessage("§buse §3/warp settings §bto see the list of all the messages");
							player.sendMessage(
									"§buse §3/warp settings [message name] §bto see the message of that message");
							player.sendMessage("§buse §3/warp settings [message name] [message] §bto edit the message");
							player.sendMessage(
									"§buse §3/warp settings [message name] extra §bto see the features that messages has");
							player.sendMessage("§buse §3/warp settings colors §bto see the color codes");
						}

					} else if (args[1].equalsIgnoreCase("colors")) {
						player.sendMessage("§9these are the colors you can use");
						player.sendMessage(
								"§0&0 §1&1 §2&2 §3&3 §4&4 §5&5 §6&6 §7&7 §8&8 §9&9 §a&a §b&b §c&c §d&d §e&e §f&f");

					} else {
						player.sendMessage(
								getConfig().getString("settings.WrongUse").replaceAll("/player/", player.getName()));
					}
					///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// warp
				} else if (args.length == 1) {
					// checks if exist
					if (!(getConfig().contains("warps." + args[0]))) {
						player.sendMessage(getConfig().getString("settings.DontExist")
								.replaceAll("/player/", player.getName()).replaceAll("/warp/", args[0]));
						return false;
					}

					if (player.getName().equalsIgnoreCase(getConfig().getString("warps." + args[0] + ".owner"))) {

						double x = getConfig().getDouble("warps." + args[0] + ".X"),
								y = getConfig().getDouble("warps." + args[0] + ".Y"),
								z = getConfig().getDouble("warps." + args[0] + ".Z");

						int pitch = getConfig().getInt("warps." + args[0] + ".pitch"),
								yaw = getConfig().getInt("warps." + args[0] + ".yaw");

						if (Bukkit.getWorld((String) getConfig().get("warps." + args[0] + ".world")) == null) {
							player.sendMessage(getConfig().getString("settings.NoWorld")
									.replaceAll("/player/", player.getName()).replaceAll("/warp/", args[0]));
							return false;
						}

						player.teleport(
								new Location(Bukkit.getWorld((String) getConfig().get("warps." + args[0] + ".world")),
										x, y, z, yaw, pitch));

						player.sendMessage(getConfig().getString("settings.Warped")
								.replaceAll("/player/", player.getName()).replaceAll("/warp/", args[0]));
					} else if (getConfig().getString("warps." + args[0] + ".private").equalsIgnoreCase("false")) {

						double x = getConfig().getDouble("warps." + args[0] + ".X"),
								y = getConfig().getDouble("warps." + args[0] + ".Y"),
								z = getConfig().getDouble("warps." + args[0] + ".Z");

						int pitch = getConfig().getInt("warps." + args[0] + ".pitch"),
								yaw = getConfig().getInt("warps." + args[0] + ".yaw");

						if (Bukkit.getWorld((String) getConfig().get("warps." + args[0] + ".world")) == null) {
							player.sendMessage(getConfig().getString("settings.NoWorld")
									.replaceAll("/player/", player.getName()).replaceAll("/warp/", args[0]));
							return false;
						}

						player.teleport(
								new Location(Bukkit.getWorld((String) getConfig().get("warps." + args[0] + ".world")),
										x, y, z, yaw, pitch));
						player.sendMessage(getConfig().getString("settings.Warped")
								.replaceAll("/player/", player.getName()).replaceAll("/warp/", args[0]));

					} else if (getConfig().getString("warps." + args[0] + ".private").equalsIgnoreCase("true")) {

						ArrayList<String> whitelist = (ArrayList<String>) getConfig()
								.getStringList("warps." + args[0] + ".whitelist");

						if (whitelist.contains(player.getName())) {

							double x = getConfig().getDouble("warps." + args[0] + ".X"),
									y = getConfig().getDouble("warps." + args[0] + ".Y"),
									z = getConfig().getDouble("warps." + args[0] + ".Z");

							int pitch = getConfig().getInt("warps." + args[0] + ".pitch"),
									yaw = getConfig().getInt("warps." + args[0] + ".yaw");

							if (Bukkit.getWorld((String) getConfig().get("warps." + args[0] + ".world")) == null) {
								player.sendMessage(getConfig().getString("settings.NoWorld")
										.replaceAll("/player/", player.getName()).replaceAll("/warp/", args[0]));
								return false;
							}

							player.teleport(new Location(
									Bukkit.getWorld((String) getConfig().get("warps." + args[0] + ".world")), x, y, z,
									yaw, pitch));
							player.sendMessage(getConfig().getString("settings.Warped")
									.replaceAll("/player/", player.getName()).replaceAll("/warp/", args[0]));

						} else {
							player.sendMessage(getConfig().getString("settings.ItsPrivate")
									.replaceAll("/player/", player.getName()).replaceAll("/warp/", args[0])
									.replaceAll("/owner/", getConfig().getString("warps." + args[0] + ".owner")));
						}
					}
				} else {
					player.sendMessage(
							getConfig().getString("settings.WrongUse").replaceAll("/player/", player.getName()));
				}
			}
		}
		return false;
	}
}
