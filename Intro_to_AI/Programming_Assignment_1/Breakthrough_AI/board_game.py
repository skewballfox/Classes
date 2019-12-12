import pygame
from string import ascii_lowercase


def N_board(object):
    """general chess board, mainly meant to handle gui functions
    game logic is to be handled by the respective game class"""
    def __init__(self):
        self.move=1
        self.create_board()
        self.white=Player()
        self.black=Player()
    
    def create_board(self):
        self.matrix = {}
        Alt=False
        for letter in "ABCDEFGH":
            for i in range(1,9):
                if Alt==False:
                    self.matrix["{}{}".format(letter,i)]=Square()
                    Alt=True
                else:
                    self.matrix["{}{}".format(letter,i)]=Square()
                    Alt=False
        del Alt
        
class Chess_board(object):
    """general chess board gui, mainly meant to handle gui functions
    game logic is to be handled by the respective game class"""
    def __init__(self,types):
        """make an 8*8 board"""
        self.move=1
        self.initial=pygame.init()
        self.colors = [(131,131,131), (222,222,222)]
        board_dimensions = 480          # Proposed physical surface size.
        self.square_dimensions = board_dimensions / 8   
        self.board_dimensions = board_dimensions * self.square_dimensions     # Adjust to exactly fit n squares.
        self.surface = pygame.display.set_mode((board_dimensions, board_dimensions))
        #self.clock=pygame.time.clock()
        self.load_pieces(types)
        self.create_board()
        self.white=Player()
        self.black=Player()
        
        

    def create_board(self):
        self.matrix = {}
        Alt=False
        for letter in "ABCDEFGH":
            for i in range(1,9):
                if Alt==False:
                    self.matrix["{}{}".format(letter,i)]=Square((222,222,222))
                    Alt=True
                else:
                    self.matrix["{}{}".format(letter,i)]=Square((131,131,131))
                    Alt=False
        del Alt
    
    def draw_board(self):
        """Draw a generalized 8x8 board"""
        for letter in "HGFEDCBA":
            Rank=self.matrix["{}{}".format(letter,1)].get_num_rank() #using the squares for the gui stuff greatly simplified things     
            color_index = Rank % 2      # Alternate starting color
            for File in range(8):       # Run through cols drawing squares
                the_square = ((File)*self.square_dimensions, (Rank)*self.square_dimensions, self.square_dimensions, self.square_dimensions)
                self.surface.fill(self.colors[color_index], the_square)
                # Now flip the color index for the next square
                color_index = (color_index + 1) % 2
                #if self.matrix["{}{}".format(letter,File+1)].owner!=None:
                #    piece_type=self.matrix["{}{}".format(letter,File+1)].owner.type.lower()
                #    piece_color=self.matrix["{}{}".format(letter,File+1)].owner.color.lower()
                #    self.surface.blit(self.images[(piece_type,piece_color)],
                #    (File*self.square_dimensions+self.offset[piece_type],Rank*self.square_dimensions+self.offset[piece_type]))

                pygame.display.flip()

    def load_pieces(self,types):
        self.offset={}
        self.images={}
        if 'pawn' in types:
            self.images[("pawn","white")]=self.pawn_white=pygame.transform.scale(pygame.image.load("pawn_white.png"),[50,30])
            self.images[("pawn","black")]=self.pawn_black=pygame.transform.scale(pygame.image.load("kisspng-battle-chess-queen-chess-piece-king-chess-game-5adecd0dd86422.0425014315245509258863.png"),[50,30])
            self.offset["pawn"]=(self.square_dimensions-self.images[("pawn","white")].get_width()) / 2

class Player:
    def __init__(self):
        self.pieces = [(None) for i in range(16)]
        self.chosen_piece=None
        self.chosen_move=None
        self.non_permitted_moves=[]
        self.Turn=False
        self.opponents_pieces=[]
    
    def choose_move(self,piece,option):
        self.chosen_piece=piece
        self.chosen_move=option
    
    def start_turn(self):
        self.Turn=True

class Square(object):
    squares=[]
    for i in range(1,9):
        for letter in "ABCDEFGH":  
                squares.append("{}{}".format(letter,i))
    
    def __init__(self,color):
        self.address=Square.squares.pop()
        self.color=color
        print(self.address)
        self._owner=None
    
    
    @property
    def owner(self):
        return self._owner
    @owner.setter
    def owner(self,piece):
        self._owner=piece
        
    
    @property
    def inspected(self):
        return self._inspected
    @inspected.setter
    def inspected(self):
        self._inspected=True
    
    def get_num_rank(self):
        print(ord(self.address[0].lower())-97)
        return ord(self.address[0].lower())-97
    def get_num_file(self):
        return ord(self.address[1])
    def get_color(self):
        return self.color


class Piece(object):
    """General Piece for board games"""
    def __init__(self,color,location):
        if color.lower()=="white" or color.lower() == 'w':
            self.color="White"
            self.direction=-1
        elif color.lower()=="black" or color.lower() =='b':
            self.color="Black"
            self.direction=1
        if isinstance(location,Square):
            self._location=location
            location.owner=self
            self.set_available_moves()
        
    
    @property
    def location(self):
        return self._location
    @location.setter
    def location(self,new_location):
        if isinstance(new_location,Square):
            (self._location).owner=None
            self._location=new_location
            new_location.owner=self
            self.options=[]
            self.set_available_moves()
    
    

    def set_available_moves(self):
        return NotImplementedError

    def __del__(self):
        try:
            print("{} lost {} at {}".format(self.color,self.type,(self._location).address))
        except:
            print("{} lost piece at {}".format(self.color,(self._location).address))