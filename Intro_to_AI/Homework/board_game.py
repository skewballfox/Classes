import pygame
pygame.init()
class Chess_board(object):
    
    def __init__(self,White,Black,piece_types):
        """make an 8*8 board"""
        self.colors = [(131,131,131), (222,222,222)]

        board_dimensions = 480           # Proposed physical surface size.
        self.square_dimensions = board_dimensions / 8   
        self.board_dimensions = board_dimensions * self.square_dimensions     # Adjust to exactly fit n squares.

        self.surface = pygame.display.set_mode((board_dimensions, board_dimensions))
        self.load_pieces(piece_types)
        self.draw_board(White,Black)
        

    def draw_board(self,White,Black):
        pygame.init()
        
        while True:
            ev = pygame.event.poll()
            if ev.type == pygame.QUIT:
                break;
            for Rank in range(8):           # Draw each row of the board.
                color_index = Rank % 2           # Alternate starting color
                for File in range(8):       # Run through cols drawing squares
                    the_square = (File*self.square_dimensions, Rank*self.square_dimensions, self.square_dimensions, self.square_dimensions)
                    self.surface.fill(self.colors[color_index], the_square)
                    # Now flip the color index for the next square
                    color_index = (color_index + 1) % 2
            for piece in White.pieces:
                print (piece)
                if piece["type"] is "pawn":
                    offset = (self.square_dimensions-self.pawn_white.get_width()) / 2
                    self.surface.blit(self.pawn_white,(piece["location"][0]*self.square_dimensions+offset,piece["location"][1]*self.square_dimensions+offset))
            for piece in Black.pieces:
                if piece["type"] is "pawn":
                    offset = (self.square_dimensions-self.pawn_black.get_width()) / 2
                    self.surface.blit(self.pawn_black,(piece["location"][0]*self.square_dimensions+offset,piece["location"][1]*self.square_dimensions+offset))
    
            pygame.display.flip()
            #pygame.quit()
    
    def load_pieces(self,types):
        if 'pawn' in types:
            self.pawn_black=pygame.transform.scale(pygame.image.load("kisspng-battle-chess-queen-chess-piece-king-chess-game-5adecd0dd86422.0425014315245509258863.png"),[50,30])
            self.pawn_white=pygame.transform.scale(pygame.image.load("pawn_white.png"),[50,30])
