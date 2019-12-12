from board_game import *

class Breakthrough_pawn(Piece):
    def __init__(self,color,location):
        self.type='pawn'
        super().__init__(color,location)
    
    def set_available_moves(self):
        options_suffix=self.location.get_num_file()+self.direction
        options_prefix=self.location.get_num_rank()
        self.options=[]
        for i in range(-1,2,1):
            self.options.append(chr((options_prefix+i)+97)+str(options_suffix))             


class Breakthrough(Chess_board):
    def __init__(self):
        """Initial state of the game"""
        super().__init__(["pawn"])
        self.create_pieces()

    def create_pieces(self):
        white_iterator,black_iterator=0,0
        self.piece_locations={'white':[],'black':[]}
        for letter in "ABCDEFGH":
            for Rank in range(1,3):
                self.white.pieces[white_iterator]=Breakthrough_pawn('white',self.matrix[letter+str(Rank)])
                self.black.opponents_pieces.append(letter+str(Rank))
                self.piece_locations
                white_iterator+=1
            for Rank in range(8,6,-1):
                self.black.pieces[black_iterator]=Breakthrough_pawn('black',self.matrix[letter+str(Rank)])
                self.white.opponents_pieces.append(letter+str(Rank))
                black_iterator+=1
        del white_iterator,black_iterator
            
    
    def actions(self, state):
        if self.move%2!=0:
            """Return a list of the allowable moves at this point."""
            raise NotImplementedError

    def result(self, state, move):
        """Return the state that results from making a move from a state."""
        raise NotImplementedError

    def utility(self, state, player):
        """Return the value of this final state to player."""
        raise NotImplementedError

    def terminal_test(self, player):
        """Return True if this is a final state for the game."""
        if player.chosen_piece.color.lower()=="white":
            if player.chosen_piece.location.get_num_file()==8:
                return True
        elif player.chosen_piece.color.lower()=="black":
            if player.chosen_piece.location.get_num_file==1:
                return True
        return False

    #def to_move(self, state):
    #    """Return the player whose move it is in this state."""
    #    return state.to_move

    def __repr__(self):
        return '<{}>'.format(self.__class__.__name__)
    
    def check_move(self,player):
            if player.chosen_piece in player.pieces and player.chosen_move in player.chosen_piece.options:
                if self.matrix[self.player_chosen_move].owner==None:
                    self.validate_move(player)
                elif self.capture_permitted(self,player)==True:
                    self.validate_move(player)
                else:
                    player.non_permitted_moves.append((player.chosen_piece,player.chosen_move))
            else:
                player.non_permitted_moves.append((player.chosen_piece,player.chosen_move))
            player.chosen_piece,player.chosen_move=None,None

    def get_piece_locations(self,player):
        player_piece_locations=[]
        for piece in player.pieces:
            player_piece_locations.append(piece.location.address)
        return player_piece_locations

    def validate_move(self,player):
        if self.matrix[self.player_chosen_move].owner.color!=player.chosen_piece.color:
            sorry=self.matrix[self.player_chosen_move].owner
            (self.matrix[self.player_chosen_move].owner).__del__()
        player.chosen_piece.location=self.matrix[self.player_chosen_move]
        player.non_permitted_moves=[]
        if self.terminal_test(self,player)==False:
            self.move+1
        else:
            raise NotImplementedError
    
    def play_game(self):
        """Play breakthrough."""
        self.initial
        while True:
            if self.move%2==1:
                if self.white.Turn==False:
                    self.white.opponents_pieces=self.get_piece_locations(self.black)
                    self.white.start_turn()
                elif self.white.chosen_move!=None:
                    self.check_move(self.white)
            else: 
                if self.black.Turn==False:
                    self.black.opponents_pieces=self.get_piece_locations(self.white)
                    self.black.start_turn()
                elif self.black.chosen_move!=None:
                    self.check_move(self.black)
                
            self.draw_board()
                #return self.utility(state, self.to_move(self.initial))
        


    

if __name__=="__main__":
    new_game=Breakthrough()
    new_game.play_game()