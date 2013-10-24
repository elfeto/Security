import time
from random import randrange
import sys

print "Hello world!"

i = 0
while True:
	if i == 0:
		print "Bienvenido a la casa abierta de la UPRRP!"
		i = i +1
	elif i == 1:
		print "Mesa de #include <girls>"
		i = i +1
	else:
		print "Vengan para jugar y la oportunidad de ganarse una paleta!"
		i = i +1
	i = i%3
	a = randrange(0,9)

	sys.stdout.write(str(a))
