clc
clear
close all

data = readtable('airports.csv','VariableNamingRule','preserve');

airport = table2cell(data(:,1));
link = table2cell(data(:,2));
node1 = table2cell(data(:,3));
lon1 = table2array(data(:,4));
lat1 = table2array(data(:,5));
type1 = table2cell(data(:,7));
node2 = table2cell(data(:,8));
lon2 = table2array(data(:,9));
lat2 = table2array(data(:,10));
type2 = table2cell(data(:,12));
type = table2cell(data(:,13));

figure
hold on
for i = 1:length(link)
    line([lon1(i) lon2(i)], [lat1(i) lat2(i)])
end
axis equal
hold off