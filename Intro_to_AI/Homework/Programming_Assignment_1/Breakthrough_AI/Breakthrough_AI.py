from board_game import *

class Breakthrough_pawn(Piece):
    def __init__(self,color,location):
        Piece.__init__(self,color,location)
    
    def available_moves(self):
        options_suffix=int(self.location[1])+self.direction
        options_prefix=ord(self.location[0].ascii_lowercase)
        self.options=[]
             

class Player:
    def __init__(self,Player_1=True):
        
        self.pieces = [(None) for i in range(16)]
        iterator=0
        for letter in "ABCDEFGH":
            if player_1 is True:
                for i in range(1,3):
                    self.pieces[iterator]=Piece()
        else:
            self.black_pieces = [{} for i in range(16)]
            for Rank in range(7,5,-1):
                for File in range(8):
                    print("r: ",Rank," f: ",File)
                    (self.pieces[(Rank-7)*-8+File])["location"]=[File,Rank]
                    (self.matrix[File][Rank])["occupied"]=True
                    (self.matrix[File][Rank])["player"]="black"
                    (self.pieces[(Rank-7)*-8+File])["type"]="pawn"
        print(self.pieces)


class Breakthrough(Chess_board):
    def __init__(self):
        """Initial state of the game"""
        Chess_board.__init__(self,["pawn"])
        
        self.move=1
        self.create_player(True)
        self.create_player(False)

    def create_player(self,player_1=True):
        self.pieces = [{} for i in range(16)]
        print(len(self.pieces))
        if player_1 is True:
            self.white_pieces = [{} for i in range(16)]
            for letter in "ABCDEFGH":
                if player_1 is True:
                    for i in range(1,3):
                        self.white_pieces[iterator]=Breakthrough_pawn("white",self.matrix["{}{}".format(letter,number)])
                else:
                    for i in range(8,6,-1):
                        self.black_pieces[iterator]=Breakthrough_pawn("black",self.matrix["{}{}".format(letter,number)])
        else:
            self.black_pieces = [{} for i in range(16)]
            for Rank in range(7,5,-1):
                for File in range(8):
                    print("r: ",Rank," f: ",File)
                    (self.black_pieces[(Rank-7)*-8+File])["location"]=[File,Rank]
                    (self.matrix[File][Rank])["occupied"]=True
                    (self.matrix[File][Rank])["player"]="black"
                    (self.pieces[(Rank-7)*-8+File])["type"]="pawn"
        print(self.pieces)
    
    def actions(self, state):
        if self.move%2!=0:
            for piece in self.white_pieces:
                piece.get_moves()
        """Return a list of the allowable moves at this point."""
        raise NotImplementedError

    def result(self, state, move):
        """Return the state that results from making a move from a state."""
        raise NotImplementedError

    def utility(self, state, player):
        """Return the value of this final state to player."""
        raise NotImplementedError

    def terminal_test(self, state):
        """Return True if this is a final state for the game."""
        return not self.actions(state)

    def to_move(self, state):
        """Return the player whose move it is in this state."""
        return state.to_move

    def __repr__(self):
        return '<{}>'.format(self.__class__.__name__)

    def play_game(self, *players):
        """Play an n-person, move-alternating game."""
        state = self.initial
        while True:
            if self.move%2==1:
                move = player(self, state)
                state = self.result(state, move)
                if self.terminal_test(state):
                    self.draw_board()
                    return self.utility(state, self.to_move(self.initial))
        


    

if __name__=="__main__":
    new_game=Breakthrough()