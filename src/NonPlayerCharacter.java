import net.minecraft.server.MinecraftServer;
import java.util.List;

public abstract class NonPlayerCharacter {
	public static List<?> players;

	// XXX: VARIABLE TYPE ON AN UPDATE
	private OEntityPlayerMP user;
	// XXX: VARIABLE TYPE ON AN UPDATE
	private OEntityTrackerEntry handler;

	public NonPlayerCharacter(String name, double x, double y, double z,
			float rotation, float pitch, int itemInHand) {
		if (players == null)
			getPlayerList();

		MinecraftServer s = etc.getServer().getMCServer();
		// XXX: VARIABLE TYPE ON AN UPDATE
		// user = new OEntityPlayerMP(s, s.e, name, new
		// OItemInWorldManager(s.e));
		user = new OEntityPlayerMP(s, s.a(0), name, new OItemInWorldManager(
				s.a(0)));
		teleportTo(x, y, z, rotation, pitch);

		// XXX: VARIABLE TYPE ON AN UPDATE
		handler = new OEntityTrackerEntry(user, 512, 1, true);
	}

	public void delete() {
		for (Object player : players) {
			// XXX: VARIABLE TYPE ON AN UPDATE
			// BaseEntity - GetId
			((OEntityPlayerMP) player).a.b(new OPacket29DestroyEntity(
					handler.a.aG));
		}
	}

	public void untrack(Player player) {
		// XXX: VARIABLE TYPE ON AN UPDATE
		if (handler.n.contains(player.getUser())) {
			handler.n.remove(player.getUser());
		}
	}

	public void broadcastPosition() {
		handler.b(players);
	}

	public void broadcastMovement() {
		handler.a(players);
	}

	/*
	 * XXX: HumanEntity tiene un getName, vemos cual es el nombre del campo
	 */
	public String getName() {
		// XXX: VARIABLE TYPE ON AN UPDATE
		return user.r;
	}

	public void setName(String name) {
		// XXX: VARIABLE TYPE ON AN UPDATE
		user.r = name;
	}

	/*
	 * XXX: En OEntity public void a(double paramDouble1, double paramDouble2,
	 * double paramDouble3) Se asigna X,Y,Z para la entidad sacamos los valores
	 * de ahi
	 */
	public double getX() {
		return user.aP;
	}

	public void setX(double x) {
		user.aP = x;
	}

	public double getY() {
		return user.aQ;
	}

	public void setY(double y) {
		user.aQ = y;
	}

	public double getZ() {
		return user.aR;
	}

	public void setZ(double z) {
		user.aR = z;
	}

	public float getRotation() {
		return user.aV;
	}

	public void setRotation(float rot) {
		user.aV = rot;
	}

	public float getPitch() {
		return user.aW;
	}

	public void setPitch(float pitch) {
		user.aW = pitch;
	}

	public int getItemInHand() {
		// XXX: VARIABLE TYPE ON AN UPDATE
		if (user.i.a[0] == null)
			return 0;
		return user.i.a[0].c;
	}

	public void teleportTo(double x, double y, double z, float rotation,
			float pitch) {
		user.b(x, y, z, rotation, pitch);
	}

	public static void getPlayerList() {
		players = etc.getServer().getMCServer().f.b;
	}
}
