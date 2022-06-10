import sys

def output(ofile, content):
	if ofile is None:
		print content.rstrip()
	else:
		ofile.write(content)

def oneline():

	# Get the block of text to convert
	print "Paste a multi-line JSON array, then type 'xxx' on the next line."
	block = []
	line = raw_input()
	while line != "xxx":
		block.append(line)
		line = raw_input()
		
	arrayText = block[0].rstrip()
	for line in block[1:]:
		arrayText += " " + line.strip()
	
	return arrayText
	
def json_compress_lists(ifilename, maxLen, ofilename):
	maxLen = int(maxLen) # Need to cast the parameter as int
	
	ifile = open(ifilename, 'r')
	if ofilename is not None:
		ofile = open(ofilename, 'w')
	else:
		ofile = None
	
	line = ifile.readline()
	while line:
		listStart = line.find("[")
		listEnd = line.find("]")
		
		# Either no bracket or both brackets [ ]
		if listStart < 0 or listEnd > listStart:
			output(ofile, line)
			line = ifile.readline()

		# Have opening bracket without closing bracket...
		else:
			# Start buffering item lines and stripped-down items
			itemLines = [line]
			items = []
			potentialLine = line.rstrip()
			
			# Search for the closing bracket ]
			
			# If you find another opening bracket: abort

			n = 0
			while listEnd < 0 and len(potentialLine) <= maxLen and n < 250:

				line = ifile.readline()
				itemLines.append(line)
				item = line.strip()
				items.append(item)
				potentialLine += " " + item
				
				#listStart = line.find("[")
				#listStart = -1
				listEnd = line.find("]")
				n += 1
			# END while
							
			if len(potentialLine) <= maxLen:
				output(ofile, potentialLine + "\n")
			else:
				for l in itemLines:
					output(ofile, l)
			# END if potentialList is short enough
			line = ifile.readline()

		# END if open list
		
	# END while lines in the file

if len(sys.argv) > 3:
	json_compress_lists(sys.argv[1], sys.argv[2], sys.argv[3])
else:
	json_compress_lists(sys.argv[1], sys.argv[2], None)
