#enigma
NAME = "Lauren McNeill Kayden Block"

#no globals >:(
nums = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]

#instructions for func -> find num, turn to int, offset, turn to string, add change index to num, add offset, turn to string, continue
def rotor(input, rotor_num): # does all the rotors yeah
    new_num = input + (rotor_num[input])
    return ((new_num))%10 

#get value from reflected
def reflect(input, reflector):
    return int(reflector[str(input)])

#shift rotor by one
def rotor_shift(rotor_num, shift): 
    new_rotor = rotor_num.copy()
    for i in range(shift):
        remove_val = new_rotor.pop(len(rotor_num)-1)
        new_rotor.insert(0, remove_val)

    return new_rotor

#enigma
#def enigma(rotors_to_use, shift, msg):
def enigma(starting_rotors, starting_positions, message):
    
    rotors_to_use = starting_rotors
    shift = starting_positions
    msg = message
    
    rotor_1 = [1, 2, 4, -3, 1, -1, 2, 0, 1, -7] 
    rotor_1_reverse = [3, -1, 7, -2, 1, -1, -4, 0, -2, -1] 
    rotor_2 = [ 0, 2, 3, -1,  2, 4, -5, -3, 0, -2] 
    rotor_2_reverse = [ 0, 5, 1, -2, 3, -3, -2, 2, 0, -4] 
    rotor_3 = [5,  8, -1, 4, -1, 3, -6, -5, -4, -3]
    rotor_3_reverse = [6, 1, 5, 1, 4, -5, 3, -4, -3, -8] 
    rotor_4 = [1, 5, 3, -1, 5, -5, 1, -3, -5, -1]
    rotor_4_reverse = [5, -1, 1, 5, 3, -3, -5, -1, 1, -5] 
    reflector = {"0": "3" , "1": "6" , "2": "8" , "3": "0" , "4": "5" , "5": "4" , "6": "1", "7": "9" , "8": "2", "9": "7"}
  
    rotors = [rotor_1, rotor_2, rotor_3, rotor_4]
    rotors_reversed = [rotor_1_reverse, rotor_2_reverse, rotor_3_reverse , rotor_4_reverse]

    num = 0

    final_msg = ""

    cur_rotor = []

    rotors_to_use_reverse = rotors_to_use.copy()
    rotors_to_use_reverse.reverse()

    used_rotors = []
    used_rotors_reverse = []
    
    used_rotors_num = []
    used_rotors_reverse_num = []

    #get rotors
    for i in range(3):
        used_rotors.append(rotors[rotors_to_use[i]-1].copy())
        used_rotors_num.append(rotors_to_use[i])
    for i in range(3):
        used_rotors_reverse.append(rotors_reversed[rotors_to_use_reverse[i]-1].copy())
        used_rotors_reverse_num.append(rotors_to_use_reverse[i])

    #starting shift
    for i in range(len(shift)):
        #if (rotors_to_use[i]-1) not in used_rotors: #stop duplicates
        used_rotors[i] = rotor_shift(used_rotors[i], shift[i])
        used_rotors_reverse[(len(shift)-i-1)] = rotor_shift(used_rotors_reverse[(len(shift)-i-1)], shift[i])
        #used_rotors.append(rotors_to_use[i]-1)

    #send letter thru enigma machine
    for i in range(len(msg)):

        #get current number from string
        num = int(msg[i])

        #first, non-reverse rotors
        for j in range(3):          
            cur_rotor = used_rotors[j] #which rotor out of list of all rotors
            num = rotor(num,cur_rotor)
            #print(f"Rotor {used_rotors_num[j]}: {num}")

        #reflect
        num = reflect(num,reflector)
        #print(f"Reversed: {num}")

        #second, reversed rotors
        for j in range(3):
            cur_rotor = used_rotors_reverse[j] #which rotor out of list of all rotors
            num = rotor(num,cur_rotor)
            #print(f"Reverse Rotor {used_rotors_reverse_num[j]}: {num}")

        #print("finished")

        #add newest later to message
        final_msg += str(num)

        #do shift after each letter
        used_rotors[2] = rotor_shift(used_rotors[2], 1)
        used_rotors_reverse[0] = rotor_shift(used_rotors_reverse[0] , 1)

        #do 2nd shift after 10
        if (((i+1) % 10) == 0) and not i == 0:

            used_rotors[1] = rotor_shift(used_rotors[1], 1)
            used_rotors_reverse[1] = rotor_shift(used_rotors_reverse[1], 1)

        #do 3rd shift after 100
        if (((i+1) % 100) == 0) and not i == 0:

            used_rotors[0] = rotor_shift(used_rotors[0], 1)
            used_rotors_reverse[2] = rotor_shift(used_rotors_reverse[2], 1)

        #print(f"Current Rotors: {used_rotors}")
        #print(f"Current Reversed Rotors: {used_rotors_reverse}")

    return final_msg

#make rotors copies

#print(rotor(1, rotor_2))
#print(reflect(8))

print(enigma([1, 2, 3],[0, 0, 0],"1234567890")) # 4805344463

print(enigma([4, 3, 1],[0, 0, 0],"48941075174104174917197107941230")) # 07510336532317397220458010476464

print(enigma([4, 4, 2],[6, 3, 8],"4463525177485309")) #7234370420921982

print(enigma([3, 1, 3],[5, 2, 0],"89")) #52

print(enigma([3, 2, 1],[8, 5, 0],"4838252453175441163480165177365946136153294997041935940093739"))

print(rotor_shift([0, 1, 2], 2))

"""
 print([1, 2, 3],[0, 0, 0],"1234567890") 
 print([4, 3, 1],[0, 0, 0],"48941075174104174917197107941230")
 print([4, 4, 2],[6, 3, 8],"4463525177485309") 
 print([3, 1, 3],[5, 2, 0],"89")
 print([3, 2, 1],[8, 5, 0],"4838252453175441163480165177365946136153294997041935940093739")
"""
