
#include <iostream>
#include <unordered_map>
#include <string>
#include <vector>
#include <filesystem>
#include <fstream>
#include <set>
#include <cmath>

class BoW {

private:

    std::fstream filestr;
    std::unordered_map<std::string,std::vector<int>> word_map;
    std::set<std::string> file_list;
    
    void update_word_map(std::string input_file);
    void add_word(std::string word);
    void interface();

public:
    BoW(std::string);
    void expand(std::string input_file="");
    void printTermFrequency();
    void printInverseDocumentFrequency();    
    

};

BoW::BoW (std::string input_file="")
{
    expand(input_file);
    interface();
}

void BoW::interface()
{
    int user_choice=0;
    bool valid_choice=false;
    while (!valid_choice)
    {
        std::cout << "\nPlease select an operation from the list below:\n";
        std::cout << "\t\t1.Expand\n\t\t2.Print term frequencies\n\t\t3.Print inverse document frequencies\n\t\t4.Exit\n";
        std::cin >> user_choice;
        if (user_choice==1) {expand("");}
        else if (user_choice==2) {printTermFrequency();}
        else if (user_choice==3){printInverseDocumentFrequency();}
        else if (user_choice==4){exit(0);}
        else{
            std::cout << "Invalid choice, Please Try again.\n\n";
        }
    }
}

void BoW::expand(std::string input_file)
{
    bool valid_input = false;
    while (valid_input==false) 
    {
        
        if (file_list.size()!=0)
        {
            std::cout << "Please input a file name add to the Bag of words: \n" << std::endl;
            std::getline(std::cin, input_file); //in case filename contains spaces
        }
        else if (input_file=="")
        {
            std::cout << "Please input a file name to initialize the Bag of words: \n" << std::endl;
            std::getline(std::cin, input_file); //in case filename contains spaces
        }

        if (!(std::filesystem::exists(input_file)))//confirm file existence  
        {
            std::cout << "Sorry, that wasn't a valid file. Please Try again. "<< std::endl << std::endl;
            input_file="";

        } else if ((file_list.insert(input_file)).second==false){
            //if the file is already in the list
            //also this adds the file to the filelist if not already there
            std::cout << "Sorry, that file has already been added. Please Try again. "<< std::endl << std::endl;
            input_file="";
        } else {
            update_word_map(input_file);
            valid_input = true;
            std::cout << "The word map has been updated to include the contents of "<< input_file <<"\n\n";
        }
    }
} 

void BoW::update_word_map(std::string input_file)
{
    filestr.open(input_file.c_str(),std::ios::in);

    std::string current_word="";
    std::string line;
    if (filestr.is_open())
    {    
        while (std::getline(filestr,line))
        {
            for (char c : line)
            {
                if (isspace(c) || (ispunct(c) && c != '\''))//counting conjunctions as words
                {   
                    add_word(current_word);
                    current_word="";
                } else {
                    current_word+=c;
                }
            }
        }
        filestr.close();
    } else {//filename was validated so should not happen
        std::cout << "Issue opening file" <<std::endl;
         
    }
}

void BoW::add_word(std::string word)
{   
    int document_index=file_list.size()-1;//used to determine current index of value
    auto it = word_map.find(word);
    if (it == word_map.end()){ //if word is new
        word_map.emplace(word,std::vector<int>(document_index+1));

    } else if (document_index>=word_map[word].size()) {//if word is new for document
        word_map[word].resize(document_index+1);
    }
    word_map[word].at(document_index)+=1;
}

void BoW::printTermFrequency()
{
    std::cout<<"Here are the term-frequencies so far:\n";
    for (auto it=word_map.begin(); it !=word_map.end(); it++)
    {
        if (file_list.size()>=it->second.size())
        {
            it->second.resize(file_list.size());
        }
        std::cout << "\nword "<< it->first<< " ";
        int j=0;
        int sum=0;
        for (std::string file : file_list)
        {
            std::cout<< " | "<<  file <<" : "<< it->second[j];
            sum+=it->second[j];
            j++;
        }
        std::cout << " -- Total : "<< sum;
    }
}
void BoW::printInverseDocumentFrequency()
{
    std::cout<<"Here are the inverse document frequencies so far:\n";
    for (auto it=word_map.begin(); it !=word_map.end(); it++)
    {
        if (file_list.size()>=it->second.size())
        {
            it->second.resize(file_list.size());
        }
        std::cout << "\nword "<< it->first<< " ";

        int doc_count=0;
        for (int j : it->second)
        {
            if (j!=0){doc_count++;}
        }
        std::cout << " "<< std::log(static_cast<double>(file_list.size())/static_cast<double>(doc_count));
    }
}
int main()
{
   
    BoW bag_of_words("");
}