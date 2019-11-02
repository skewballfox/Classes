import board_game

class Player():
    def __init__(self, player_1=True):
        self.score=0
        self.pieces = [{} for i in range(16)]
        print(len(self.pieces))
        if player_1 is True:
            self.piece_color="white"
            self.direction = -1
            for Rank in range(2):
                for File in range(8):
                    print((Rank*8)+File)
                    print("r: ",Rank," f: ",File)
                    (self.pieces[(Rank*8)+File])["location"]=[File,Rank]
                    (self.pieces[(Rank*8)+File])["type"]="pawn"
        else:
            self.piece_color="black"
            self.direction = 1
            for Rank in range(7,5,-1):
                for File in range(8):
                    print("r: ",Rank," f: ",File)
                    (self.pieces[(Rank-7)*-8+File])["location"]=[File,Rank]
                    (self.pieces[(Rank-7)*-8+File])["type"]="pawn"
        print(self.pieces)

class Breakthrough():
    def __init__(self):
        """Initial state of the game"""
        
        
        self.move=1
        self.white=Player(True)
        self.black=Player(False)
        board=board_game.Chess_board(self.white,self.black,["pawn"])

        


    

if __name__=="__main__":
    new_game=Breakthrough()