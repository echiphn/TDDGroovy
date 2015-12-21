package script

def shell= new GroovyShell()
def calcResult=shell.evaluate("12+13")
assert 25 == calcResult