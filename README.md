# better-than-sniffers
A BTW-like plugin.

# How it works
uh basically just a really sketchy programming tutorial

Items are registered through the Items object, and you can create a instance of that item to give to a player using Items#asItemStack(). 
Blocks are registered through the Blocks object, and I use hot garbage packet nonsense to display these on the client. Since the server's registry gets shifted to the left, kinda like this
server  client

test    air

air     stone

stone   dirt

issues occur. So what I did was basically just subtract one from all the block IDs in the packets when they are sent to the client. It would probably be easier just to use a Fabric mixin but no actually, since I still need to modify packets to map the custom blocks anyway.
